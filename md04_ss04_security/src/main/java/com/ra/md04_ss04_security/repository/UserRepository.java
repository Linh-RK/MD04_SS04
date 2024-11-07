package com.ra.md04_ss04_security.repository;

import com.ra.md04_ss04_security.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserName(String username);
    List<User> findByUserName(String username);
}
