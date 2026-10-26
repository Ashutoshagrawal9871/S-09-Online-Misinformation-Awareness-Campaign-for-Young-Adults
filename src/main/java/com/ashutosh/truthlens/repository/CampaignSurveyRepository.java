package com.ashutosh.truthlens.repository;

import com.ashutosh.truthlens.model.CampaignSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignSurveyRepository extends JpaRepository<CampaignSurvey, Long> {

    List<CampaignSurvey> findByParticipantId(Long userId);

    List<CampaignSurvey> findBySurveyType(String surveyType);
}
