package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

    List<Workshop> findByStatus(String status);

    List<Workshop> findByCategory(String category);
}
