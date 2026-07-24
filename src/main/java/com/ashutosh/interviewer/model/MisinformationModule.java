package com.ashutosh.interviewer.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String category; // e.g., "Deepfakes", "Phishing", "Fake News Detection"

    @Column(nullable = false)
    private String contentUrl;
}
