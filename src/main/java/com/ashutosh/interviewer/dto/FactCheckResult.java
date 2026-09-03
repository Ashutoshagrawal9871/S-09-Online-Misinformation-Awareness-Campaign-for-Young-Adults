package com.ashutosh.interviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactCheckResult {

    private Long queryId;
    private String claim;
    private String verdict; // VERIFIED_FALSE, MISLEADING, VERIFIED_TRUE, UNVERIFIED
    private Integer confidenceScore; // 0-100
    private String summary;
    private String detailedAnalysis;
    private List<String> factCheckFrameworkSteps; // SIFT steps: Stop, Investigate source, Find better coverage, Trace claims
    private List<String> verificationSources; // BOOM Live, Alt News, Snopes, PIB Fact Check
    private List<String> warningFlags; // Indicators of manipulation / viral bait
    private String recommendedAction;
}
