package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.WorkshopRegisterRequest;
import com.ashutosh.truthlens.exception.ResourceNotFoundException;
import com.ashutosh.truthlens.model.User;
import com.ashutosh.truthlens.model.Workshop;
import com.ashutosh.truthlens.model.WorkshopRegistration;
import com.ashutosh.truthlens.repository.UserRepository;
import com.ashutosh.truthlens.repository.WorkshopRegistrationRepository;
import com.ashutosh.truthlens.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final WorkshopRegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @Override
    public Optional<Workshop> getWorkshopById(Long id) {
        return workshopRepository.findById(id);
    }

    @Override
    @Transactional
    public Workshop createWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Override
    @Transactional
    public WorkshopRegistration registerForWorkshop(WorkshopRegisterRequest request) {
        Workshop workshop = workshopRepository.findById(request.getWorkshopId())
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found with ID: " + request.getWorkshopId()));

        if (workshop.getCurrentRegistrations() >= workshop.getMaxCapacity()) {
            throw new IllegalArgumentException("This workshop is already at full capacity.");
        }
        if (registrationRepository.existsByWorkshopIdAndParticipantEmail(request.getWorkshopId(), request.getParticipantEmail())) {
            throw new IllegalArgumentException("This email is already registered for this workshop.");
        }

        User participant = null;
        if (request.getUserId() != null) {
            participant = userRepository.findById(request.getUserId()).orElse(null);
        }

        WorkshopRegistration registration = WorkshopRegistration.builder()
                .workshop(workshop)
                .participant(participant)
                .participantName(request.getParticipantName())
                .participantEmail(request.getParticipantEmail())
                .organizationOrCollege(request.getOrganizationOrCollege())
                .registeredAt(LocalDateTime.now())
                .attended(false)
                .build();

        // Increment registration counter
        workshop.setCurrentRegistrations(workshop.getCurrentRegistrations() + 1);
        workshopRepository.save(workshop);

        return registrationRepository.save(registration);
    }

    @Override
    public List<WorkshopRegistration> getRegistrationsForWorkshop(Long workshopId) {
        return registrationRepository.findByWorkshopId(workshopId);
    }
}
