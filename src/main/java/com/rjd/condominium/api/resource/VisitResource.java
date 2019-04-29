package com.rjd.condominium.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rjd.condominium.api.event.ResourceCreatedEvent;
import com.rjd.condominium.api.model.Visit;
import com.rjd.condominium.api.repository.VisitRepository;
import com.rjd.condominium.api.repository.filter.VisitFilter;
import com.rjd.condominium.api.repository.projection.VisitSummary;
import com.rjd.condominium.api.service.VisitService;

@RestController
@RequestMapping("/visits")
public class VisitResource {

	@Autowired
	private VisitRepository visitRepository;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Visit> getOpenVisits(Pageable pageable) {
		return visitRepository.findByOpen(true, pageable);
	}
	
	@GetMapping(params = "summary")
	public Page<VisitSummary> getOpenVisitsSummary(VisitFilter visitFilter, Pageable pageable) {
		return visitRepository.summariseOpen(visitFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Visit> insertVisit(@Valid @RequestBody Visit visit, HttpServletResponse response) {
		Visit visitSaved = visitService.insertVisit(visit);
		publisher.publishEvent(new ResourceCreatedEvent(this, visitSaved.getId(), response));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(visitSaved);
	}
	
	@GetMapping("/all")
	public Page<Visit> getVisits(VisitFilter visitFilter, Pageable pageable) {
		return visitRepository.filtrate(visitFilter, pageable);
	}
	
	@GetMapping(path = "/all", params = "summary")
	public Page<VisitSummary> getVisitsSummarise(VisitFilter visitFilter, Pageable pageable) {
		return visitRepository.summariseAll(visitFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisit(@PathVariable Long id) {
		Optional<Visit> visit = visitRepository.findById(id);
		return visit.isPresent() ? ResponseEntity.ok(visit.get()) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/open")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateOpenVisit(@PathVariable Long id, @RequestBody Boolean open) {
		visitService.updateOpenVisit(id, open);
	}
}
