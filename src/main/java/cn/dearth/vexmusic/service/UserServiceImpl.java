package cn.dearth.vexmusic.service;

import cn.dearth.vexmusic.dto.UserDto;
import cn.dearth.vexmusic.mapper.UserMapper;
import cn.dearth.vexmusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dearth
 */
@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public List<UserDto> list() {
        // 通过流式计算调用toDto方法把从数据库中拿到的实体类转成Dto
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
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
