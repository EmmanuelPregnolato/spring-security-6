package com.dailycodebuffer.security;

import com.dailycodebuffer.security.controller.UserController;
import org.springframework.security.core.userdetails.User;
import com.dailycodebuffer.security.service.AuthService;
import com.dailycodebuffer.security.service.CustomUserDetailsService;
import com.dailycodebuffer.security.service.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // Spring Boot 3.4+
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

// Imports statiques essentiels pour la lisibilité
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

@WebMvcTest(UserController.class) // Charge uniquement la couche Web pour ce Controller
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Tes 3 services simulés (Mocks) dans le contexte Spring MVC
    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldReturnUserByEmail() throws Exception {
        // 1. GIVEN : Création d'un UserDetails Spring Security
        UserDetails userDetails = User.builder()
                .username("emmanuel").roles("ADMIN")
                .password("encoded_password").build();


        Mockito.when(customUserDetailsService.loadUserByUsername("emmanuel"))
                .thenReturn(userDetails);

        // 2. WHEN & THEN
        mockMvc.perform(get("/api/users")
                        .param("username", "emmanuel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("emmanuel"));
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        // GIVEN
        Mockito.when(customUserDetailsService.loadUserByUsername("unknown@example.com"))
                .thenThrow(new UsernameNotFoundException("User not found"));

        // WHEN & THEN
        mockMvc.perform(get("/api/users")
                        .param("email", "unknown@example.com"))
                .andExpect(status().isNotFound());
    }
}
