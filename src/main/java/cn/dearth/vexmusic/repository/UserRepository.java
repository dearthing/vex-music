package cn.dearth.vexmusic.repository;

import cn.dearth.vexmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dearth
 */

public interface UserRepository extends JpaRepository<User, String> {

    User getUserByUsername(String username);
}
