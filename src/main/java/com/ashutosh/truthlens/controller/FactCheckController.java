package com.ashutosh.truthlens.controller;

import com.ashutosh.truthlens.dto.FactCheckRequest;
import com.ashutosh.truthlens.dto.FactCheckResult;
import com.ashutosh.truthlens.model.FactCheckQuery;
import com.ashutosh.truthlens.service.FactCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fact-check")
@RequiredArgsConstructor
@Tag(name = "Fact-Checking & Misinformation Verification", description = "AI & SIFT-powered rumor debunking engine")
@CrossOrigin(origins = "*")
public class FactCheckController {

    private final FactCheckService factCheckService;

    @PostMapping("/verify")
    @Operation(summary = "Verify a viral message, health myth, or social media claim")
    public ResponseEntity<FactCheckResult> verifyClaim(@Valid @RequestBody FactCheckRequest request) {
        FactCheckResult result = factCheckService.verifyClaim(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/recent")
    @Operation(summary = "Get recent fact check queries and debunks")
    public ResponseEntity<List<FactCheckQuery>> getRecentQueries() {
        return ResponseEntity.ok(factCheckService.getRecentQueries());
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get fact check queries by category (HEALTH, POLITICS, FINANCIAL, DEEPFAKES)")
    public ResponseEntity<List<FactCheckQuery>> getQueriesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(factCheckService.getQueriesByCategory(category));
    }
}
