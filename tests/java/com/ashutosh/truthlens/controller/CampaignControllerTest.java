package com.ashutosh.truthlens.controller;

import com.ashutosh.truthlens.dto.SurveyResponseRequest;
import com.ashutosh.truthlens.model.CampaignSurvey;
import com.ashutosh.truthlens.model.MisinformationModule;
import com.ashutosh.truthlens.service.CampaignService;
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
public class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CampaignService campaignService;

    @Test
    @DisplayName("GET /api/v1/campaign/modules should return list of modules")
    void getAllModules_ShouldReturnModulesList() throws Exception {
        MisinformationModule module = MisinformationModule.builder()
                .id(1L)
                .title("Spotting Deepfakes")
                .category("DIGITAL_LITERACY")
                .build();

        Mockito.when(campaignService.getAllModules()).thenReturn(List.of(module));

        mockMvc.perform(get("/api/v1/campaign/modules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spotting Deepfakes"))
                .andExpect(jsonPath("$[0].category").value("DIGITAL_LITERACY"));
    }

    @Test
    @DisplayName("POST /api/v1/campaign/surveys/submit with valid payload should return 200 OK")
    void submitSurvey_WhenValidRequest_ShouldReturnSavedSurvey() throws Exception {
        SurveyResponseRequest request = SurveyResponseRequest.builder()
                .userId(101L)
                .surveyType("BASELINE")
                .score(8)
                .feedback("Great module!")
                .build();

        CampaignSurvey savedSurvey = CampaignSurvey.builder()
                .id(101L)
                .surveyType("BASELINE")
                .score(8)
                .feedback("Great module!")
                .build();

        Mockito.when(campaignService.submitSurveyResponse(any(SurveyResponseRequest.class))).thenReturn(savedSurvey);

        mockMvc.perform(post("/api/v1/campaign/surveys/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andExpect(jsonPath("$.score").value(8));
    }
}
