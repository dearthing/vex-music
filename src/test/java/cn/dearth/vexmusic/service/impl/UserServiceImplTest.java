package cn.dearth.vexmusic.service.impl;

import cn.dearth.vexmusic.core.entity.User;
import cn.dearth.vexmusic.core.enums.Gender;
import cn.dearth.vexmusic.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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