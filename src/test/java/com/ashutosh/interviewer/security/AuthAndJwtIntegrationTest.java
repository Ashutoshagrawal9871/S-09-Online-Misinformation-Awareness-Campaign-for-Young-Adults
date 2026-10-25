package com.ashutosh.interviewer.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthAndJwtIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String loginAsAdmin() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        JsonNode body = objectMapper.readTree(result.getResponse().getContentAsString());
        return body.get("token").asText();
    }

    @Test
    @DisplayName("Seeded admin can log in and receives a JWT")
    void loginWithSeededAdmin_ShouldReturnToken() throws Exception {
        String token = loginAsAdmin();
        org.junit.jupiter.api.Assertions.assertFalse(token.isBlank());
    }

    @Test
    @DisplayName("Admin endpoint is accessible with a valid JWT")
    void adminEndpoint_WithJwt_ShouldReturnOk() throws Exception {
        String token = loginAsAdmin();

        mockMvc.perform(get("/api/v1/campaign/surveys")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Admin endpoint is blocked without a JWT")
    void adminEndpoint_WithoutJwt_ShouldBeForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/campaign/surveys"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Invalid credentials return 400 instead of a server error")
    void loginWithWrongPassword_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"wrong-password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("Public registration cannot self-assign the ADMIN role")
    void registerWithAdminRole_ShouldBeForcedToStudent() throws Exception {
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"username":"security_check","email":"security.check@example.com","password":"password123","role":"ADMIN"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }
}
