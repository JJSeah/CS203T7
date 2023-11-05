package com.example.electric.respository;

import com.example.electric.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieve User by Email
     *
     * This method retrieves a User entity by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object with the specified email, or null if not found.
     */
    User findUserByEmail(@Param("email") String email);

    /**
     * Find User by Email
     *
     * This method attempts to find a User entity by their email address.
     *
     * @param email The email address of the user to find.
     * @return An Optional<User> object containing the User entity if found, otherwise it's empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Check User Existence by Email
     *
     * This method checks if a user with the specified email address exists.
     *
     * @param email The email address to check.
     * @return true if a user with the email address exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieve User by Email
     *
     * This method retrieves a User entity by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object with the specified email, or null if not found.
     */
    User getUserByEmail(@Param("email") String email);

}
