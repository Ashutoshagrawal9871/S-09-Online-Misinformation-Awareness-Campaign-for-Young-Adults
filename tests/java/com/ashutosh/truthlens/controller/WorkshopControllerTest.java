package com.ashutosh.truthlens.controller;

import com.ashutosh.truthlens.dto.WorkshopRegisterRequest;
import com.ashutosh.truthlens.model.Workshop;
import com.ashutosh.truthlens.model.WorkshopRegistration;
import com.ashutosh.truthlens.service.WorkshopService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkshopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WorkshopService workshopService;

    @Test
    @DisplayName("GET /api/v1/workshops should return list of workshops")
    void getAllWorkshops_ShouldReturnList() throws Exception {
        Workshop w = Workshop.builder()
                .id(1L)
                .title("Workshop 1: Fact-Checking Tools")
                .description("Hands on fact checking")
                .instructor("Prof. Sharma")
                .scheduledAt(LocalDateTime.now().plusDays(2))
                .location("Auditorium")
                .build();

        Mockito.when(workshopService.getAllWorkshops()).thenReturn(List.of(w));

        mockMvc.perform(get("/api/v1/workshops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Workshop 1: Fact-Checking Tools"));
    }

    @Test
    @DisplayName("POST /api/v1/workshops/register should register participant")
    void registerForWorkshop_ShouldReturnRegistration() throws Exception {
        WorkshopRegisterRequest req = WorkshopRegisterRequest.builder()
                .workshopId(1L)
                .participantName("Priya")
                .participantEmail("priya@example.com")
                .organizationOrCollege("Engineering College")
                .build();

        WorkshopRegistration reg = WorkshopRegistration.builder()
                .id(10L)
                .participantName("Priya")
                .participantEmail("priya@example.com")
                .registeredAt(LocalDateTime.now())
                .build();

        Mockito.when(workshopService.registerForWorkshop(any(WorkshopRegisterRequest.class))).thenReturn(reg);

        mockMvc.perform(post("/api/v1/workshops/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.participantName").value("Priya"));
    }
}
