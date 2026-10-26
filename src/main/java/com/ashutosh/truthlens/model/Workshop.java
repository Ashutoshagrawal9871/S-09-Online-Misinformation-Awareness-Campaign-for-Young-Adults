package com.ashutosh.truthlens.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "workshops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Column(nullable = false)
    private String location; // e.g. "Main Campus Auditorium & Zoom Live"

    @Column
    private String meetingLink;

    @Column(nullable = false)
    @Builder.Default
    private Integer maxCapacity = 100;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentRegistrations = 0;

    @Column(nullable = false)
    @Builder.Default
    private String status = "UPCOMING"; // UPCOMING, IN_PROGRESS, COMPLETED

    @Column(nullable = false)
    private String category; // e.g. "FACT_CHECKING_TOOLS", "DEEPFAKE_DETECTION"
}
