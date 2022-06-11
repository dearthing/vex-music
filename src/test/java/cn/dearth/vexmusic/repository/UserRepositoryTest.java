package cn.dearth.vexmusic.repository;

import cn.dearth.vexmusic.entity.User;
import cn.dearth.vexmusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dearth
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void getUserByUsername() {

        User user = User.builder()
                .username("Vex")
                .nickname("lil Tiger")
                .gender(Gender.MALE)
                .enabled(true)
                .locked(false)
                .lastLoginIp("127.0.0.1")
                .lastLoginTime(new Date())
                .build();

        // 由于save返回的user,返回的roles为null
        User savedUser = repository.save(user);

        User userVex = repository.getUserByUsername("Vex");

        // 注意这个toString 并不会给出id
        // 因为lombok的@Data注解重写的toString方法只有类上的属性
        System.out.println(userVex.toString());
    }
}