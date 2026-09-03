package com.ashutosh.interviewer.repository;

import com.ashutosh.interviewer.model.FactCheckQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactCheckQueryRepository extends JpaRepository<FactCheckQuery, Long> {

    List<FactCheckQuery> findTop10ByOrderByCheckedAtDesc();

    List<FactCheckQuery> findByCategory(String category);

    List<FactCheckQuery> findByVerdict(String verdict);
}
