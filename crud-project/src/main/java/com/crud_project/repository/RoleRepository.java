package com.crud_project.repository;

import java.util.Optional;

import com.crud_project.model.enums.ERole;
import com.crud_project.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}