package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.MisinformationModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MisinformationModuleRepository extends JpaRepository<MisinformationModule, Long> {

    List<MisinformationModule> findByCategory(String category);
}