package com.ashutosh.interviewer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactCheckRequest {

    @NotBlank(message = "Claim text or query is required")
    private String claim;

    private String category; // HEALTH, POLITICS, FINANCIAL, GENERAL

    private String sourceUrl;

    private String sourcePlatform;

    private String context;
}
