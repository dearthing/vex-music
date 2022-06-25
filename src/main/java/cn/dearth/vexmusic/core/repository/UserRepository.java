package cn.dearth.vexmusic.core.repository;

import cn.dearth.vexmusic.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author dearth
 */

public interface UserRepository extends JpaRepository<User, String> {

    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
