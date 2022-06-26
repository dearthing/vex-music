package cn.dearth.vexmusic.core.controller;

import cn.dearth.vexmusic.core.dto.request.RoleCreateRequest;
import cn.dearth.vexmusic.core.mapper.RoleMapper;
import cn.dearth.vexmusic.core.service.RoleService;
import cn.dearth.vexmusic.core.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dearth
 */
@Api("角色接口")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Resource
    RoleService roleService;

    @Resource
    RoleMapper roleMapper;

    @ApiOperation("管理员创建角色")
    @PostMapping("/")
    RoleVo create(@RequestBody RoleCreateRequest roleCreateRequest) {
        return roleMapper.toVo(roleService.create(roleCreateRequest));
    }

    @ApiOperation("根据用户id获取角色信息")
    @GetMapping("/{id}")
    RoleVo get(@PathVariable String id) {
        return roleMapper.toVo(roleService.get(id));
    }


}
