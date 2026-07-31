package com.ashutosh.interviewer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponseRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Survey type is required")
    private String surveyType; // "BASELINE" or "POST_CAMPAIGN"

    @NotNull(message = "Score is required")
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 10, message = "Score cannot exceed 10")
    private Integer score;

    private String feedback;
}
