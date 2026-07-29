package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.CampaignSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignSurveyRepository extends JpaRepository<CampaignSurvey, Long> {

    List<CampaignSurvey> findByParticipantId(Long userId);

    List<CampaignSurvey> findBySurveyType(String surveyType);
}
