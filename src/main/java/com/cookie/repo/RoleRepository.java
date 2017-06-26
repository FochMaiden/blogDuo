package com.cookie.repo;

import com.cookie.integration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FochMaiden
 */
@Repository("roleRepo")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
