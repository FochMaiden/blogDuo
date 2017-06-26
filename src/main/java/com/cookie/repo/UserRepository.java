package com.cookie.repo;

import com.cookie.integration.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FochMaiden
 */
@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
