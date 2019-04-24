package com.rjd.condominium.api.repository.visit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rjd.condominium.api.model.Visit;
import com.rjd.condominium.api.repository.filter.VisitFilter;
import com.rjd.condominium.api.repository.projection.VisitSummary;

public interface VisitRepositoryQuery {

	public Page<Visit> filtrate(VisitFilter visitFilter, Pageable pageable);
	public Page<VisitSummary> summarise(VisitFilter visitFilter, Pageable pageable);
}
