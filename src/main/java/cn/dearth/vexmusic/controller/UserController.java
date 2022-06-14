package cn.dearth.vexmusic.controller;

import cn.dearth.vexmusic.dto.UserCreateDto;
import cn.dearth.vexmusic.mapper.UserMapper;
import cn.dearth.vexmusic.service.UserService;
import cn.dearth.vexmusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dearth
 */
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @GetMapping("/")
    public List<UserVo> list(){
        return userService.list().stream().map(userMapper::toVo).collect(Collectors.toList());
    }

    @PostMapping("/")
    public UserVo create(@RequestBody UserCreateDto userCreateDto) {
        return userMapper.toVo(userService.create(userCreateDto));
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
