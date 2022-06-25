package cn.dearth.vexmusic.core.service;

import cn.dearth.vexmusic.core.dto.request.TokenCreateRequest;
import cn.dearth.vexmusic.core.dto.UserDto;
import cn.dearth.vexmusic.core.dto.request.UserCreateRequest;
import cn.dearth.vexmusic.core.dto.request.UserUpdateRequest;
import cn.dearth.vexmusic.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dearth
 */
public interface UserService extends UserDetailsService {


    UserDto create(UserCreateRequest userCreateRequest);

    @Override
    User loadUserByUsername(String username);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
