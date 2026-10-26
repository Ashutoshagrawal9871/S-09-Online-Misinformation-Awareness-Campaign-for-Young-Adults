package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.SurveyResponseRequest;
import com.ashutosh.truthlens.model.CampaignSurvey;
import com.ashutosh.truthlens.model.MisinformationModule;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    List<MisinformationModule> getAllModules();

    Optional<MisinformationModule> getModuleById(Long id);

    List<MisinformationModule> getModulesByCategory(String category);

    MisinformationModule createModule(MisinformationModule module);

    CampaignSurvey submitSurveyResponse(SurveyResponseRequest request);

    List<CampaignSurvey> getUserSurveyHistory(Long userId);

    List<CampaignSurvey> getSurveysByType(String surveyType);

    List<CampaignSurvey> getAllSurveys();
}
