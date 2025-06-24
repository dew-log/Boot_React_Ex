package org.suhodo.cardatabase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.cardatabase.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // username으로 정보 조회하는 Query Method
    Optional<AppUser> findByUsername(String username);
}