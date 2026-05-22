package com.group3.SIS.modules.users.repository;
import com.group3.SIS.modules.users.entity.usersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<usersEntity, Long> {
    Optional<usersEntity> findByUsername(String username);
    Optional<usersEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
