package com.rjd.condominium.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rjd.condominium.api.model.Visit;
import com.rjd.condominium.api.repository.VisitRepository;

@Service
public class VisitService {

	@Autowired
	private VisitRepository visitRepository;
	
	public Visit insertVisit(Visit visit) {
		visit.setStartDate(LocalDateTime.now());
		visit.setOpen(true);
		
		return visitRepository.save(visit);
	}
	
	public void updateOpenVisit(Long id, Boolean open) {
		Visit visit = searhVisitById(id);
		visit.setOpen(open);
		visit.setFinalDate(LocalDateTime.now());
		
		visitRepository.save(visit);
	}
	
	public Visit searhVisitById(Long id) {
		Optional<Visit> visit = visitRepository.findById(id);
		
		if (!visit.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return visit.get();
	}
}
