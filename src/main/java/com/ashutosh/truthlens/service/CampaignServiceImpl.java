package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.SurveyResponseRequest;
import com.ashutosh.truthlens.exception.ResourceNotFoundException;
import com.ashutosh.truthlens.model.CampaignSurvey;
import com.ashutosh.truthlens.model.MisinformationModule;
import com.ashutosh.truthlens.model.User;
import com.ashutosh.truthlens.repository.CampaignSurveyRepository;
import com.ashutosh.truthlens.repository.MisinformationModuleRepository;
import com.ashutosh.truthlens.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Optional<MisinformationModule> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public List<MisinformationModule> getModulesByCategory(String category) {
        return moduleRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public MisinformationModule createModule(MisinformationModule module) {
        return moduleRepository.save(module);
    }

    @Override
    @Transactional
    public CampaignSurvey submitSurveyResponse(SurveyResponseRequest request) {
        User participant = null;
        if (request.getUserId() != null) {
            participant = userRepository.findById(request.getUserId())
                    .orElseGet(() -> {
                        // Create user fallback
                        User newUser = User.builder()
                                .username("participant_" + request.getUserId())
                                .email(request.getParticipantEmail() != null ? request.getParticipantEmail() : "participant" + request.getUserId() + "@truthlens.org")
                                .password("noop")
                                .role("STUDENT")
                                .build();
                        return userRepository.save(newUser);
                    });
        } else if (request.getParticipantEmail() != null && !request.getParticipantEmail().isBlank()) {
            participant = userRepository.findByEmail(request.getParticipantEmail())
                    .orElseGet(() -> {
                        User newUser = User.builder()
                                .username(request.getParticipantEmail().split("@")[0])
                                .email(request.getParticipantEmail())
                                .password("noop")
                                .role("STUDENT")
                                .build();
                        return userRepository.save(newUser);
                    });
        }

        CampaignSurvey survey = CampaignSurvey.builder()
                .participant(participant)
                .surveyType(request.getSurveyType() != null ? request.getSurveyType().toUpperCase() : "BASELINE")
                .score(request.getScore() != null ? request.getScore() : 5)
                .verificationFrequency(request.getVerificationFrequency() != null ? request.getVerificationFrequency() : 3)
                .primaryNewsSource(request.getPrimaryNewsSource() != null ? request.getPrimaryNewsSource() : "WHATSAPP")
                .feedback(request.getFeedback())
                .completedAt(LocalDateTime.now())
                .build();

        return surveyRepository.save(survey);
    }

    @Override
    public List<CampaignSurvey> getUserSurveyHistory(Long userId) {
        return surveyRepository.findByParticipantId(userId);
    }

    @Override
    public List<CampaignSurvey> getSurveysByType(String surveyType) {
        return surveyRepository.findBySurveyType(surveyType.toUpperCase());
    }

    @Override
    public List<CampaignSurvey> getAllSurveys() {
        return surveyRepository.findAll();
    }
}
