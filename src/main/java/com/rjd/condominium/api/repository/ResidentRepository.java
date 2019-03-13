package com.rjd.condominium.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rjd.condominium.api.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

	public Page<Resident> findByNameContaining(String name, Pageable pageable);
}
