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
import com.rjd.condominium.api.model.Resident;
import com.rjd.condominium.api.repository.ResidentRepository;
import com.rjd.condominium.api.service.ResidentService;

@RestController
@RequestMapping("/residents")
public class ResidentResource {

	@Autowired
	private ResidentRepository residentRepository;
	
	@Autowired
	private ResidentService residentService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Resident> getResidents(@RequestParam (required = false, defaultValue = "%") String name, Pageable pageable) {
		return residentRepository.findByNameContaining(name, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Resident> insertResident(@RequestBody @Valid Resident resident, HttpServletResponse response) {
		
		Resident residentSaved = residentService.insertResident(resident);
		publisher.publishEvent(new ResourceCreatedEvent(this, residentSaved.getId(), response));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(residentSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Resident> getResident(@PathVariable Long id) {
		Optional<Resident> resident = residentRepository.findById(id);
		return resident.isPresent() ? ResponseEntity.ok(resident.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteResident(@PathVariable Long id) {
		residentRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Resident> updateResident(@RequestBody @Valid Resident resident, @PathVariable Long id) {
		Resident residentSaved = residentService.updateResident(id, resident);
		return ResponseEntity.ok(residentSaved);
	}
}
