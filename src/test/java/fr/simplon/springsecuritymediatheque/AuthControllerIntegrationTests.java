package fr.simplon.springsecuritymediatheque;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.simplon.springsecuritymediatheque.model.dtos.users.LoginRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterRequest;
import fr.simplon.springsecuritymediatheque.model.entity.RoleType;
import fr.simplon.springsecuritymediatheque.model.entity.User;
import fr.simplon.springsecuritymediatheque.model.mappers.UserMapper;
import fr.simplon.springsecuritymediatheque.repository.UserRepository;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUserWithHashedPassword() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .username("admin")
                .email("admin@example.com")
                .password("securepassword")
                .authorities(Set.of(RoleType.ADMIN))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void shouldAuthenticateAndReturnToken() throws Exception {
        RegisterRequest register = RegisterRequest.builder()
                .username("admin")
                .email("admin@example.com")
                .password("securepassword")
                .authorities(Set.of(RoleType.ADMIN))
                .build();

        LoginRequest login = LoginRequest.builder()
                .email(register.email())
                .password(register.password())
                .build();

        User user = userRepository.save(UserMapper.registerRequestToUser(register));

        System.out.println(user.getId());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("admin@example.com"));
    }
}
