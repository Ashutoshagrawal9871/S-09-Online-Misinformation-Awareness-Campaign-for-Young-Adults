package com.ashutosh.interviewer.controller;

import com.ashutosh.interviewer.dto.SurveyResponseRequest;
import com.ashutosh.interviewer.model.CampaignSurvey;
import com.ashutosh.interviewer.model.MisinformationModule;
import com.ashutosh.interviewer.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaign")
@RequiredArgsConstructor
@Tag(name = "Campaign & Survey Management", description = "Endpoints for misinformation awareness modules and survey submissions")
@CrossOrigin(origins = "*")
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/modules")
    @Operation(summary = "Get all misinformation awareness modules")
    public ResponseEntity<List<MisinformationModule>> getAllModules(
            @RequestParam(required = false) String category) {
        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(campaignService.getModulesByCategory(category));
        }
        return ResponseEntity.ok(campaignService.getAllModules());
    }

    @GetMapping("/modules/{id}")
    @Operation(summary = "Get awareness module by ID")
    public ResponseEntity<MisinformationModule> getModuleById(@PathVariable Long id) {
        return campaignService.getModuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/modules")
    @Operation(summary = "Create a new awareness module")
    public ResponseEntity<MisinformationModule> createModule(@Valid @RequestBody MisinformationModule module) {
        return new ResponseEntity<>(campaignService.createModule(module), HttpStatus.CREATED);
    }

    @PostMapping("/surveys/submit")
    @Operation(summary = "Submit baseline or post-campaign survey response")
    public ResponseEntity<CampaignSurvey> submitSurveyResponse(@Valid @RequestBody SurveyResponseRequest request) {
        CampaignSurvey savedSurvey = campaignService.submitSurveyResponse(request);
        return ResponseEntity.ok(savedSurvey);
    }

    @GetMapping("/surveys")
    @Operation(summary = "Get all surveys or filter by type (BASELINE, POST_CAMPAIGN)")
    public ResponseEntity<List<CampaignSurvey>> getSurveys(@RequestParam(required = false) String type) {
        if (type != null && !type.isBlank()) {
            return ResponseEntity.ok(campaignService.getSurveysByType(type));
        }
        return ResponseEntity.ok(campaignService.getAllSurveys());
    }

    @GetMapping("/surveys/history/{userId}")
    @Operation(summary = "Get survey history for a specific participant")
    public ResponseEntity<List<CampaignSurvey>> getUserSurveyHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(campaignService.getUserSurveyHistory(userId));
    }
}
