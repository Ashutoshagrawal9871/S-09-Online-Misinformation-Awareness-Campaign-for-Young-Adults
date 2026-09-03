package com.ashutosh.interviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "campaign_surveys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnoreProperties({"password", "createdAt", "updatedAt"})
    private User participant;

    @Column(nullable = false)
    private String surveyType; // e.g., "BASELINE" or "POST_CAMPAIGN"

    @Column(nullable = false)
    private Integer score; // Susceptibility score or verification score (1-10)

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column
    private Integer verificationFrequency; // 1-5 scale (How often do you check facts before sharing)

    @Column
    private String primaryNewsSource; // "WHATSAPP", "INSTAGRAM", "TWITTER_X", "YOUTUBE", "NEWS_SITES"

    @Column(nullable = false)
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        if (completedAt == null) {
            completedAt = LocalDateTime.now();
        }
    }
}
