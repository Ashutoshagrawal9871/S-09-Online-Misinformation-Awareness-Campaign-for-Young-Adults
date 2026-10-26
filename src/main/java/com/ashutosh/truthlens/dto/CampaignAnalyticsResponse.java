package com.ashutosh.truthlens.dto;

import com.ashutosh.truthlens.model.CampaignMetric;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignAnalyticsResponse {

    private long totalImpressions;
    private long totalEngagements;
    private long totalWorkshopRegistrations;
    private long totalWorkshopAttendees;
    private long peerMultipliersTrained;
    private long totalSurveysCompleted;
    private double baselineAvgSusceptibilityScore; // Higher score = more susceptible in baseline (e.g. 7.4 / 10)
    private double postCampaignAvgSusceptibilityScore; // Lower score = less susceptible post campaign (e.g. 2.8 / 10)
    private double susceptibilityReductionPercentage; // e.g. 62.1% reduction
    private double factCheckingBehaviorShiftPercentage; // e.g. +58.4% increase in pre-share fact verification
    private List<CampaignMetric> keyMetrics;
    private Map<String, Long> surveyChannelDistribution;
    private Map<String, Object> weeklyMilestoneProgress;
}
