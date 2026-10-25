package com.ashutosh.interviewer.controller;

import com.ashutosh.interviewer.dto.CampaignAnalyticsResponse;
import com.ashutosh.interviewer.service.AnalyticsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AnalyticsService analyticsService;

    @Test
    @DisplayName("GET /api/v1/analytics should return campaign impact analytics")
    void getComprehensiveAnalytics_ShouldReturnAnalytics() throws Exception {
        CampaignAnalyticsResponse response = CampaignAnalyticsResponse.builder()
                .totalImpressions(1415L)
                .totalWorkshopAttendees(118L)
                .peerMultipliersTrained(12L)
                .susceptibilityReductionPercentage(62.1)
                .factCheckingBehaviorShiftPercentage(58.4)
                .build();

        Mockito.when(analyticsService.getComprehensiveAnalytics()).thenReturn(response);

        mockMvc.perform(get("/api/v1/analytics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalImpressions").value(1415))
                .andExpect(jsonPath("$.peerMultipliersTrained").value(12))
                .andExpect(jsonPath("$.susceptibilityReductionPercentage").value(62.1));
    }
}
