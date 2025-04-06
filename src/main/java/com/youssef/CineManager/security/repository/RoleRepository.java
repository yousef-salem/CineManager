package com.youssef.CineManager.security.repository;


import com.youssef.CineManager.security.model.ERole;
import com.youssef.CineManager.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}