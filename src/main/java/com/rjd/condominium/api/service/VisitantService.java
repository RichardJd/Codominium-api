package com.rjd.condominium.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rjd.condominium.api.model.Visitant;
import com.rjd.condominium.api.repository.VisitantRepository;

@Service
public class VisitantService {

	@Autowired
	private VisitantRepository visitantRepository;
	
	public Visitant insertVisitant(Visitant visitant) {
		return visitantRepository.save(visitant);
	}
	
	public Visitant updateVisitant(Long id, Visitant visitant) {
		Visitant visitantSaved = searchVisitantById(id);
		BeanUtils.copyProperties(visitant, visitantSaved, "id");
		
		return visitantRepository.save(visitantSaved);
	}
	
	private Visitant searchVisitantById(Long id) {
		Optional<Visitant> visitant = visitantRepository.findById(id);
		
		if (!visitant.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return visitant.get();
	}
}
