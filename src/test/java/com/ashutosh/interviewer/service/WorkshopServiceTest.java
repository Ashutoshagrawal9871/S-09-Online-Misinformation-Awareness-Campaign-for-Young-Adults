package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.WorkshopRegisterRequest;
import com.ashutosh.interviewer.model.Workshop;
import com.ashutosh.interviewer.model.WorkshopRegistration;
import com.ashutosh.interviewer.repository.UserRepository;
import com.ashutosh.interviewer.repository.WorkshopRegistrationRepository;
import com.ashutosh.interviewer.repository.WorkshopRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkshopServiceTest {

    @Mock
    private WorkshopRepository workshopRepository;

    @Mock
    private WorkshopRegistrationRepository registrationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WorkshopServiceImpl workshopService;

    private WorkshopRegisterRequest request() {
        return WorkshopRegisterRequest.builder()
                .workshopId(1L)
                .participantName("Priya Sharma")
                .participantEmail("priya@example.com")
                .organizationOrCollege("Journalism")
                .build();
    }

    @Test
    @DisplayName("Registration is rejected when the workshop is full")
    void registerForWorkshop_WhenFull_ShouldThrow() {
        Workshop workshop = Workshop.builder()
                .id(1L)
                .maxCapacity(10)
                .currentRegistrations(10)
                .build();
        when(workshopRepository.findById(1L)).thenReturn(Optional.of(workshop));

        assertThrows(IllegalArgumentException.class,
                () -> workshopService.registerForWorkshop(request()));

        verify(registrationRepository, never()).save(any());
    }

    @Test
    @DisplayName("Registration is rejected for a duplicate email")
    void registerForWorkshop_WhenDuplicateEmail_ShouldThrow() {
        Workshop workshop = Workshop.builder()
                .id(1L)
                .maxCapacity(10)
                .currentRegistrations(4)
                .build();
        when(workshopRepository.findById(1L)).thenReturn(Optional.of(workshop));
        when(registrationRepository.existsByWorkshopIdAndParticipantEmail(1L, "priya@example.com"))
                .thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> workshopService.registerForWorkshop(request()));

        verify(registrationRepository, never()).save(any());
    }

    @Test
    @DisplayName("Successful registration increments the capacity counter")
    void registerForWorkshop_WhenValid_ShouldIncrementCounter() {
        Workshop workshop = Workshop.builder()
                .id(1L)
                .maxCapacity(10)
                .currentRegistrations(4)
                .build();
        WorkshopRegistration saved = WorkshopRegistration.builder()
                .id(9L)
                .workshop(workshop)
                .participantName("Priya Sharma")
                .participantEmail("priya@example.com")
                .build();

        when(workshopRepository.findById(1L)).thenReturn(Optional.of(workshop));
        when(registrationRepository.existsByWorkshopIdAndParticipantEmail(1L, "priya@example.com"))
                .thenReturn(false);
        when(workshopRepository.save(workshop)).thenReturn(workshop);
        when(registrationRepository.save(any(WorkshopRegistration.class))).thenReturn(saved);

        WorkshopRegistration result = workshopService.registerForWorkshop(request());

        assertEquals(9L, result.getId());
        assertEquals(5, workshop.getCurrentRegistrations());
        verify(workshopRepository).save(workshop);
    }
}
