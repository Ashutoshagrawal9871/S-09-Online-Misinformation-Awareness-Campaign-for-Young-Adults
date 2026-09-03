package com.ashutosh.interviewer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "misinformation_modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MisinformationModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String category; // e.g., "DIGITAL_LITERACY", "DEEPFAKES", "WHATSAPP_RUMORS", "HEALTH_MYTHS", "FACT_CHECKING_TOOLS"

    @Column(nullable = false)
    private String contentUrl;

    @Column
    @Builder.Default
    private String difficulty = "BEGINNER";

    @Column
    @Builder.Default
    private Integer estimatedMinutes = 15;

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
