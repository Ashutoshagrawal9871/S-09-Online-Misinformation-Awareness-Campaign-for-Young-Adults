package com.ashutosh.interviewer.dto;

import jakarta.validation.constraints.Email;
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
public class WorkshopRegisterRequest {

    @NotNull(message = "Workshop ID is required")
    private Long workshopId;

    private Long userId;

    @NotBlank(message = "Participant name is required")
    private String participantName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String participantEmail;

    private String organizationOrCollege;
}
