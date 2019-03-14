package com.rjd.condominium.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rjd.condominium.api.model.Resident;
import com.rjd.condominium.api.repository.ResidentRepository;

@Service
public class ResidentService {

	@Autowired
	private ResidentRepository residentRepository;
	
	public Resident Update(Long id, Resident resident) {
		Resident residentSaved = searchResidentById(id);
		BeanUtils.copyProperties(resident, residentSaved, "id");
		
		return residentRepository.save(residentSaved);
	}
	
	private Resident searchResidentById(Long id) {
		Optional<Resident> resident = residentRepository.findById(id);
		
		if (!resident.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return resident.get();
	}
}
