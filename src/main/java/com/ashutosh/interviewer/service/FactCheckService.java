package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.FactCheckRequest;
import com.ashutosh.interviewer.dto.FactCheckResult;
import com.ashutosh.interviewer.model.FactCheckQuery;

import java.util.List;

public interface FactCheckService {

    FactCheckResult verifyClaim(FactCheckRequest request);

    List<FactCheckQuery> getRecentQueries();

    List<FactCheckQuery> getQueriesByCategory(String category);
}
