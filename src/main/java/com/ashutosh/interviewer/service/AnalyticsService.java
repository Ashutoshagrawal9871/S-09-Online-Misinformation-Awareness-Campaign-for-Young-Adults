package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.CampaignAnalyticsResponse;
import com.ashutosh.interviewer.model.CampaignMetric;

import java.util.List;

public interface AnalyticsService {

    CampaignAnalyticsResponse getComprehensiveAnalytics();

    List<CampaignMetric> getAllMetrics();

    CampaignMetric updateMetric(String key, Double value);
}
