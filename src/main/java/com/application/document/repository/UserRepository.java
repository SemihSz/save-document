package com.application.document.repository;

import com.application.document.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Semih, 2.07.2023
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}