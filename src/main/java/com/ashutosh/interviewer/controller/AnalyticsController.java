package com.ashutosh.interviewer.controller;

import com.ashutosh.interviewer.dto.CampaignAnalyticsResponse;
import com.ashutosh.interviewer.model.CampaignMetric;
import com.ashutosh.interviewer.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Tag(name = "Campaign Analytics & Impact", description = "Real-time metrics, reach analytics, and pre/post survey behavior changes")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping({"", "/overview"})
    @Operation(summary = "Get comprehensive campaign impact metrics and analytics")
    public ResponseEntity<CampaignAnalyticsResponse> getComprehensiveAnalytics() {
        return ResponseEntity.ok(analyticsService.getComprehensiveAnalytics());
    }

    @GetMapping("/metrics")
    @Operation(summary = "Get all raw campaign KPI metrics")
    public ResponseEntity<List<CampaignMetric>> getAllMetrics() {
        return ResponseEntity.ok(analyticsService.getAllMetrics());
    }

    @PutMapping("/metrics/{key}")
    @Operation(summary = "Update or upsert a campaign metric value")
    public ResponseEntity<CampaignMetric> updateMetric(
            @PathVariable String key,
            @RequestBody Map<String, Double> payload) {
        Double value = payload.getOrDefault("value", 0.0);
        return ResponseEntity.ok(analyticsService.updateMetric(key, value));
    }
}
