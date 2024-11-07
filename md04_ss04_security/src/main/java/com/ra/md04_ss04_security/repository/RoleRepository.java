package com.ra.md04_ss04_security.repository;

import com.ra.md04_ss04_security.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role, Long> {

     Role findRoleByRoleName(String roleName) ;
}
