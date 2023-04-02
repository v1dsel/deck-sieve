package com.cloakyloki.dao.repository;

import com.cloakyloki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}