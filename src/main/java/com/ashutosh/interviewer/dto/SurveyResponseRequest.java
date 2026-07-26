package com.ashutosh.interviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseRequest {

    private Long userId;
    private String surveyType; // "BASELINE" or "POST_CAMPAIGN"
    private Integer score;
    private String feedback;
}
