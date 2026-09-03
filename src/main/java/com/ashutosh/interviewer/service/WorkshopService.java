package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.WorkshopRegisterRequest;
import com.ashutosh.interviewer.model.Workshop;
import com.ashutosh.interviewer.model.WorkshopRegistration;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {

    List<Workshop> getAllWorkshops();

    Optional<Workshop> getWorkshopById(Long id);

    Workshop createWorkshop(Workshop workshop);

    WorkshopRegistration registerForWorkshop(WorkshopRegisterRequest request);

    List<WorkshopRegistration> getRegistrationsForWorkshop(Long workshopId);
}
