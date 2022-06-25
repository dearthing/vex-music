package cn.dearth.vexmusic.core.service.impl;

import cn.dearth.vexmusic.core.constant.AuthenticationConfigConstants;
import cn.dearth.vexmusic.core.dto.request.TokenCreateRequest;
import cn.dearth.vexmusic.core.dto.request.UserUpdateRequest;
import cn.dearth.vexmusic.core.repository.UserRepository;
import cn.dearth.vexmusic.core.service.UserService;
import cn.dearth.vexmusic.core.dto.UserDto;
import cn.dearth.vexmusic.core.dto.request.UserCreateRequest;
import cn.dearth.vexmusic.core.entity.User;
import cn.dearth.vexmusic.core.exception.BizException;
import cn.dearth.vexmusic.core.exception.ExceptionType;
import cn.dearth.vexmusic.core.mapper.UserMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author dearth
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;


    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        User user = userMapper.createEntity(userCreateRequest);
        checkUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }

    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Override
    public UserDto get(String id) {
        // TODO 重构
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userMapper.toDto(optionalUser.get());
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        // TODO 重构
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(optionalUser.get(), userUpdateRequest)));
    }

    @Override
    public void delete(String id) {
        // TODO 重构
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = loadUserByUsername(authentication.getName());
        return userMapper.toDto(currentUser);
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
