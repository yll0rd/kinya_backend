package com.kinya.kinya_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<UserDetails> findByEmail(String email);
    User findByEmail(String email);
//    boolean existsByEmail(String email);
}
