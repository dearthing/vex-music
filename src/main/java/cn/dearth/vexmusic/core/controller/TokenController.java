package cn.dearth.vexmusic.core.controller;

import cn.dearth.vexmusic.core.dto.request.TokenCreateRequest;
import cn.dearth.vexmusic.core.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dearth
 */
@Api(tags = "用户登录获取token")
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Resource
    UserService userService;

    @PostMapping
    String create(@RequestBody TokenCreateRequest tokenCreateRequest) {
        return userService.createToken(tokenCreateRequest);
    }

}
