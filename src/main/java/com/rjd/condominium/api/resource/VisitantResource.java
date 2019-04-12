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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rjd.condominium.api.event.ResourceCreatedEvent;
import com.rjd.condominium.api.model.Visitant;
import com.rjd.condominium.api.repository.VisitantRepository;
import com.rjd.condominium.api.service.VisitantService;

@RestController
@RequestMapping("/visitants")
public class VisitantResource {

	@Autowired
	private VisitantRepository visitantRepository;
	
	@Autowired
	private VisitantService visitantService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Visitant> getVisitants(@RequestParam(required = false, defaultValue = "%") String name, Pageable pageable) {
		return visitantRepository.findByNameContaining(name, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Visitant> insertVisitant(@Valid @RequestBody Visitant visitant, HttpServletResponse response) {
		Visitant visitantSaved = visitantService.insertVisitant(visitant);
		publisher.publishEvent(new ResourceCreatedEvent(this, visitantSaved.getId(), response));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(visitantSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visitant> getVisitant(@PathVariable Long id) {
		Optional<Visitant> visitant = visitantRepository.findById(id);
		
		return visitant.isPresent() ? ResponseEntity.ok(visitant.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVisitant(@PathVariable Long id) {
		visitantRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Visitant> updateVisitant(@PathVariable Long id, @Valid @RequestBody Visitant visitant) {
		Visitant visitantSaved = visitantService.updateVisitant(id, visitant);
		return ResponseEntity.ok(visitantSaved);
	}
}
