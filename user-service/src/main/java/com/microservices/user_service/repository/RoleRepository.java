package com.microservices.user_service.repository;

import com.microservices.user_service.enums.AppRole;
import com.microservices.user_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(AppRole role);
}
