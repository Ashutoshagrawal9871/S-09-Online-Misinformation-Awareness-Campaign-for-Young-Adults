package com.ashutosh.interviewer.controller;

import com.ashutosh.interviewer.dto.FactCheckRequest;
import com.ashutosh.interviewer.dto.FactCheckResult;
import com.ashutosh.interviewer.service.FactCheckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FactCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FactCheckService factCheckService;

    @Test
    @DisplayName("POST /api/v1/fact-check/verify should return debunk result")
    void verifyClaim_ShouldReturnResult() throws Exception {
        FactCheckRequest request = FactCheckRequest.builder()
                .claim("Drinking hot lemon water cures cancer")
                .category("HEALTH")
                .build();

        FactCheckResult result = FactCheckResult.builder()
                .queryId(1L)
                .claim(request.getClaim())
                .verdict("VERIFIED_FALSE")
                .confidenceScore(95)
                .summary("Drinking lemon water does not cure cancer.")
                .verificationSources(List.of("American Cancer Society", "BOOM Live"))
                .build();

        Mockito.when(factCheckService.verifyClaim(any(FactCheckRequest.class))).thenReturn(result);

        mockMvc.perform(post("/api/v1/fact-check/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.verdict").value("VERIFIED_FALSE"))
                .andExpect(jsonPath("$.confidenceScore").value(95));
    }

    @Test
    @DisplayName("GET /api/v1/fact-check/recent should return recent queries")
    void getRecentQueries_ShouldReturnList() throws Exception {
        Mockito.when(factCheckService.getRecentQueries()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/fact-check/recent"))
                .andExpect(status().isOk());
    }
}
