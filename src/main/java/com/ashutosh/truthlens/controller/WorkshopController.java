package com.ashutosh.truthlens.controller;

import com.ashutosh.truthlens.dto.WorkshopRegisterRequest;
import com.ashutosh.truthlens.model.Workshop;
import com.ashutosh.truthlens.model.WorkshopRegistration;
import com.ashutosh.truthlens.service.WorkshopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
@RequiredArgsConstructor
@Tag(name = "Workshop Management", description = "Endpoints for fact-checking workshops and registrations")
@CrossOrigin(origins = "*")
public class WorkshopController {

    private final WorkshopService workshopService;

    @GetMapping
    @Operation(summary = "Get all scheduled and completed workshops")
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        return ResponseEntity.ok(workshopService.getAllWorkshops());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get workshop details by ID")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable Long id) {
        return workshopService.getWorkshopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new workshop session")
    public ResponseEntity<Workshop> createWorkshop(@Valid @RequestBody Workshop workshop) {
        return new ResponseEntity<>(workshopService.createWorkshop(workshop), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a student / participant for a workshop")
    public ResponseEntity<WorkshopRegistration> registerForWorkshop(@Valid @RequestBody WorkshopRegisterRequest request) {
        WorkshopRegistration registration = workshopService.registerForWorkshop(request);
        return ResponseEntity.ok(registration);
    }

    @GetMapping("/{id}/registrations")
    @Operation(summary = "Get all attendee registrations for a workshop")
    public ResponseEntity<List<WorkshopRegistration>> getWorkshopRegistrations(@PathVariable Long id) {
        return ResponseEntity.ok(workshopService.getRegistrationsForWorkshop(id));
    }
}
