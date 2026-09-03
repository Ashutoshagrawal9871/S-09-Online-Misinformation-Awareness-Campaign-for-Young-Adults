package com.ashutosh.interviewer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fact_check_queries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactCheckQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String claimText;

    @Column(nullable = false)
    private String category; // HEALTH, POLITICS, FINANCIAL, SCAM, GENERAL

    @Column(nullable = false)
    private String verdict; // VERIFIED_FALSE, MISLEADING, VERIFIED_TRUE, UNVERIFIED

    @Column(nullable = false)
    private Integer confidenceScore; // 0-100

    @Column(columnDefinition = "TEXT", nullable = false)
    private String debunkSummary;

    @Column(columnDefinition = "TEXT")
    private String primarySources; // JSON array or comma separated list of fact-checking URLs

    @Column(nullable = false)
    private String verificationMethod; // "SIFT_FRAMEWORK", "BOOM_LIVE", "ALT_NEWS", "SNOPES", "REVERSE_IMAGE"

    @Column(nullable = false)
    private LocalDateTime checkedAt;

    @PrePersist
    protected void onCreate() {
        if (checkedAt == null) {
            checkedAt = LocalDateTime.now();
        }
    }
}
