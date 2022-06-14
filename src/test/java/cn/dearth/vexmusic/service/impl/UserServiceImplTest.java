package cn.dearth.vexmusic.service.impl;

import cn.dearth.vexmusic.entity.User;
import cn.dearth.vexmusic.enums.Gender;
import cn.dearth.vexmusic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dearth
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;


    @Test
    void getUserByUsername(){
        User user = User.builder()
                .username("Vex")
                .nickname("lil Tiger")
                .gender(Gender.MALE)
                .enabled(true)
                .locked(false)
                .lastLoginIp("127.0.0.1")
                .lastLoginTime(new Date())
                .build();

        userRepository.save(user);

    }



}