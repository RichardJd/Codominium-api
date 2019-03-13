package com.rjd.condominium.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rjd.condominium.api.model.Resident;
import com.rjd.condominium.api.repository.ResidentRepository;

@RestController
@RequestMapping("/residents")
public class ResidentResource {

	@Autowired
	private ResidentRepository residentRepository;
	
	@GetMapping
	public Page<Resident> getResidents(@RequestParam (required = false, defaultValue = "%") String name, Pageable pageable) {
		return residentRepository.findByNameContaining(name, pageable);
	}
}
