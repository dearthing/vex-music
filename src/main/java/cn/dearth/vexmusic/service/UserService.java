package cn.dearth.vexmusic.service;

import cn.dearth.vexmusic.dto.UserCreateDto;
import cn.dearth.vexmusic.dto.UserDto;
import cn.dearth.vexmusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author dearth
 */
public interface UserService extends UserDetailsService {

    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
