package com.ventulus95.ouathjwt.repository;

import com.ventulus95.ouathjwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email=?1 AND provider =?2", nativeQuery = true)
    Optional<User> findByEmailAndProvider( String email, String provider);

    boolean existsByEmail(String email);
}
