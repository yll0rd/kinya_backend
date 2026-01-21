package com.kinya.kinya_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    //    Optional<UserDetails> findByEmail(String email);
    Optional<User> findByEmail(String email);
//    boolean existsByEmail(String email);

    default void upsertByEmail(User user) {
        Optional<User> existingUser = findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            User _existingUser = existingUser.get();
            _existingUser.setName(user.getName());
            _existingUser.setPassword(user.getPassword());
            _existingUser.setRole(user.getRole());
            save(_existingUser);
            return;
        }
        save(user);
    }
}
