package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.CampaignMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignMetricRepository extends JpaRepository<CampaignMetric, Long> {

    Optional<CampaignMetric> findByMetricKey(String metricKey);
}
