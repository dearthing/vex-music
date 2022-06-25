package cn.dearth.vexmusic.core.controller;

import cn.dearth.vexmusic.core.dto.request.UserCreateRequest;
import cn.dearth.vexmusic.core.dto.request.UserUpdateRequest;
import cn.dearth.vexmusic.core.mapper.UserMapper;
import cn.dearth.vexmusic.core.service.UserService;
import cn.dearth.vexmusic.core.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @author dearth
 */

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "用户管理")
public class UserController {

    UserService userService;

    UserMapper userMapper;


    @ApiOperation("用户检索")
    @GetMapping("/search")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @ApiOperation("创建用户")
    @PostMapping("/")
    @RolesAllowed({"ROLE_ADMIN"})
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    @ApiOperation("获取当前用户")
    @GetMapping("/me")
    UserVo me() {
        return userMapper.toVo(userService.getCurrentUser());
    }

    @ApiOperation("根据id获取用户")
    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }


    @ApiOperation("更新用户信息")
    @PutMapping("/{id}")
    UserVo update(@PathVariable String id,
                  @RequestBody @Validated UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    void delete(@PathVariable String id) {
        userService.delete(id);
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
