package com.dailycodebuffer.security;

import com.dailycodebuffer.security.entity.User;
import com.dailycodebuffer.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest // In memory DB H2
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Devrait trouver un utilisateur par son email")
    void shouldFindUserByEmail() {
        // Given
        User user = new User();
        user.setUsername("emmanuel@example.com");
        user.setPassword("encodedPassword");
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByUsername("emmanuel@example.com");

        // Then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("emmanuel@example.com");
    }
}
