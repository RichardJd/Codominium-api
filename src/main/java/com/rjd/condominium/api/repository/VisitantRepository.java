package com.rjd.condominium.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rjd.condominium.api.model.Visitant;

public interface VisitantRepository extends JpaRepository<Visitant, Long> {

	public Page<Visitant> findByNameContaining(String name, Pageable pageable);
}
