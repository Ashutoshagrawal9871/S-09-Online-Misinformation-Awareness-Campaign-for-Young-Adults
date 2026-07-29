package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.SurveyResponseRequest;
import com.ashutosh.interviewer.model.CampaignSurvey;
import com.ashutosh.interviewer.model.MisinformationModule;
import com.ashutosh.interviewer.model.User;
import com.ashutosh.interviewer.repository.CampaignSurveyRepository;
import com.ashutosh.interviewer.repository.MisinformationModuleRepository;
import com.ashutosh.interviewer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final MisinformationModuleRepository moduleRepository;
    private final CampaignSurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Override
    public List<MisinformationModule> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public List<MisinformationModule> getModulesByCategory(String category) {
        return moduleRepository.findByCategory(category);
    }

    @Override
    public CampaignSurvey submitSurveyResponse(SurveyResponseRequest request) {
        User participant = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + request.getUserId()));

        CampaignSurvey survey = CampaignSurvey.builder()
                .participant(participant)
                .surveyType(request.getSurveyType())
                .score(request.getScore())
                .feedback(request.getFeedback())
                .completedAt(LocalDateTime.now())
                .build();

        return surveyRepository.save(survey);
    }

    @Override
    public List<CampaignSurvey> getUserSurveyHistory(Long userId) {
        return surveyRepository.findByParticipantId(userId);
    }
}
