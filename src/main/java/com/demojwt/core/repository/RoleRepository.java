package com.demojwt.core.repository;

import com.demojwt.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String name);
}