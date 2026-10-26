package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.WorkshopRegisterRequest;
import com.ashutosh.truthlens.model.Workshop;
import com.ashutosh.truthlens.model.WorkshopRegistration;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {

    List<Workshop> getAllWorkshops();

    Optional<Workshop> getWorkshopById(Long id);

    Workshop createWorkshop(Workshop workshop);

    WorkshopRegistration registerForWorkshop(WorkshopRegisterRequest request);

    List<WorkshopRegistration> getRegistrationsForWorkshop(Long workshopId);
}
