package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.WorkshopRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRegistrationRepository extends JpaRepository<WorkshopRegistration, Long> {

    List<WorkshopRegistration> findByWorkshopId(Long workshopId);

    List<WorkshopRegistration> findByParticipantEmail(String email);

    boolean existsByWorkshopIdAndParticipantEmail(Long workshopId, String participantEmail);
}
