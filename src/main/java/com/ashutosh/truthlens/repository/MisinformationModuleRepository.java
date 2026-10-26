package com.ashutosh.truthlens.repository;

import com.ashutosh.truthlens.model.MisinformationModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MisinformationModuleRepository extends JpaRepository<MisinformationModule, Long> {

    List<MisinformationModule> findByCategory(String category);
}