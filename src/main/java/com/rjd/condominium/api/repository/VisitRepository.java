package com.rjd.condominium.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rjd.condominium.api.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

	public Page<Visit> findByOpen(Boolean open, Pageable pageable);
}
