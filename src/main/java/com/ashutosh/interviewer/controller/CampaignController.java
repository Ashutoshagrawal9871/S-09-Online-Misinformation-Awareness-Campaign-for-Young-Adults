package com.ashutosh.interviewer.controller;

import com.ashutosh.interviewer.dto.SurveyResponseRequest;
import com.ashutosh.interviewer.model.CampaignSurvey;
import com.ashutosh.interviewer.model.MisinformationModule;
import com.ashutosh.interviewer.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/modules")
    public ResponseEntity<List<MisinformationModule>> getAllModules() {
        return ResponseEntity.ok(campaignService.getAllModules());
    }

    @GetMapping("/modules/category/{category}")
    public ResponseEntity<List<MisinformationModule>> getModulesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(campaignService.getModulesByCategory(category));
    }

    @PostMapping("/surveys/submit")
    public ResponseEntity<CampaignSurvey> submitSurvey(@Valid @RequestBody SurveyResponseRequest request) {
        return ResponseEntity.ok(campaignService.submitSurveyResponse(request));
    }

    @GetMapping("/surveys/user/{userId}")
    public ResponseEntity<List<CampaignSurvey>> getUserSurveys(@PathVariable Long userId) {
        return ResponseEntity.ok(campaignService.getUserSurveyHistory(userId));
    }
}
