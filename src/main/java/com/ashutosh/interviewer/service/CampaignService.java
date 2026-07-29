package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.SurveyResponseRequest;
import com.ashutosh.interviewer.model.CampaignSurvey;
import com.ashutosh.interviewer.model.MisinformationModule;

import java.util.List;

public interface CampaignService {

    List<MisinformationModule> getAllModules();

    List<MisinformationModule> getModulesByCategory(String category);

    CampaignSurvey submitSurveyResponse(SurveyResponseRequest request);

    List<CampaignSurvey> getUserSurveyHistory(Long userId);
}