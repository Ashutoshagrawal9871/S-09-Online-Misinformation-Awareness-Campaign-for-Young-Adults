package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.SurveyResponseRequest;
import com.ashutosh.truthlens.model.CampaignSurvey;
import com.ashutosh.truthlens.model.MisinformationModule;
import com.ashutosh.truthlens.model.User;
import com.ashutosh.truthlens.repository.CampaignSurveyRepository;
import com.ashutosh.truthlens.repository.MisinformationModuleRepository;
import com.ashutosh.truthlens.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    @Mock
    private MisinformationModuleRepository moduleRepository;

    @Mock
    private CampaignSurveyRepository surveyRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    private MisinformationModule sampleModule;

    @BeforeEach
    void setUp() {
        sampleModule = MisinformationModule.builder()
                .id(1L)
                .title("SIFT Framework")
                .category("DIGITAL_LITERACY")
                .contentUrl("https://truthlens.org/sift")
                .build();
    }

    @Test
    @DisplayName("getAllModules should return all repository modules")
    void getAllModules_ShouldReturnList() {
        when(moduleRepository.findAll()).thenReturn(List.of(sampleModule));

        List<MisinformationModule> result = campaignService.getAllModules();

        assertEquals(1, result.size());
        assertEquals("SIFT Framework", result.get(0).getTitle());
        verify(moduleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("submitSurveyResponse should save and return survey")
    void submitSurveyResponse_ShouldSaveSurvey() {
        User user = User.builder().id(1L).username("testuser").email("test@truthlens.org").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        CampaignSurvey survey = CampaignSurvey.builder()
                .id(5L)
                .participant(user)
                .surveyType("BASELINE")
                .score(7)
                .build();
        when(surveyRepository.save(any(CampaignSurvey.class))).thenReturn(survey);

        SurveyResponseRequest req = SurveyResponseRequest.builder()
                .userId(1L)
                .surveyType("BASELINE")
                .score(7)
                .build();

        CampaignSurvey result = campaignService.submitSurveyResponse(req);

        assertNotNull(result);
        assertEquals(7, result.getScore());
        assertEquals("BASELINE", result.getSurveyType());
    }
}
