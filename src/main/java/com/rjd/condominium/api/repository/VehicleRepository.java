package com.rjd.condominium.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rjd.condominium.api.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	public Page<Vehicle> findByPlateContaining(String plate, Pageable pageable);
}
