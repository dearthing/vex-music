package cn.dearth.vexmusic.service.impl;

import cn.dearth.vexmusic.dto.UserCreateDto;
import cn.dearth.vexmusic.dto.UserDto;
import cn.dearth.vexmusic.entity.User;
import cn.dearth.vexmusic.exception.BizException;
import cn.dearth.vexmusic.exception.ExceptionType;
import cn.dearth.vexmusic.mapper.UserMapper;
import cn.dearth.vexmusic.repository.UserRepository;
import cn.dearth.vexmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dearth
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User user = userMapper.createEntity(userCreateDto);
        // 检查用户名是否重复,若重复则抛出异常
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

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
