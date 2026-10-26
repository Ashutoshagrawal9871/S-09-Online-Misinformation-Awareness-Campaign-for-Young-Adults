package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.CampaignAnalyticsResponse;
import com.ashutosh.truthlens.model.CampaignMetric;

import java.util.List;

public interface AnalyticsService {

    CampaignAnalyticsResponse getComprehensiveAnalytics();

    List<CampaignMetric> getAllMetrics();

    CampaignMetric updateMetric(String key, Double value);
}
