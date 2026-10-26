package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.FactCheckRequest;
import com.ashutosh.truthlens.dto.FactCheckResult;
import com.ashutosh.truthlens.model.FactCheckQuery;

import java.util.List;

public interface FactCheckService {

    FactCheckResult verifyClaim(FactCheckRequest request);

    List<FactCheckQuery> getRecentQueries();

    List<FactCheckQuery> getQueriesByCategory(String category);
}
