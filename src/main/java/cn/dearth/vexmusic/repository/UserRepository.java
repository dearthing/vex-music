package cn.dearth.vexmusic.repository;

import cn.dearth.vexmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author dearth
 */

public interface UserRepository extends JpaRepository<User, String> {

    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
