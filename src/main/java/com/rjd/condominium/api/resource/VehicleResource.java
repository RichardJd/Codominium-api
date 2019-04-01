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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rjd.condominium.api.event.ResourceCreatedEvent;
import com.rjd.condominium.api.model.Vehicle;
import com.rjd.condominium.api.repository.VehicleRepository;

@RestController
@RequestMapping("/vehicles")
public class VehicleResource {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Vehicle> getVehicles(@RequestParam(required = false, defaultValue = "%") String plate, Pageable pageable){
		return vehicleRepository.findByPlateContaining(plate, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Vehicle> insertVehicle(@Valid @RequestBody Vehicle vehicle, HttpServletResponse response) {
		Vehicle vehicleSaved = vehicleRepository.save(vehicle);
		publisher.publishEvent(new ResourceCreatedEvent(this, vehicleSaved.getId(), response));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vehicleSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		
		return vehicle.isPresent() ? ResponseEntity.ok(vehicle.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVehicle(@PathVariable Long id) {
		vehicleRepository.deleteById(id);
	}
}
