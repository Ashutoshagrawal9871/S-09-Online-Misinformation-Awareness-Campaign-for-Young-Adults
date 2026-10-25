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

    private Long userId;

    private String participantEmail;

    @NotBlank(message = "Survey type is required")
    private String surveyType; // "BASELINE" or "POST_CAMPAIGN"

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must be non-negative")
    @Max(value = 10, message = "Score cannot exceed 10")
    private Integer score;

    @Min(value = 1, message = "Verification frequency must be between 1 and 5")
    @Max(value = 5, message = "Verification frequency must be between 1 and 5")
    private Integer verificationFrequency; // 1 to 5 scale

    private String primaryNewsSource; // "WHATSAPP", "INSTAGRAM", "TWITTER_X", "YOUTUBE", "NEWS_SITES"

    private String feedback;
}
