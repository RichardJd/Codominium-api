package com.rjd.condominium.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjd.condominium.api.event.ResourceCreatedEvent;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {

	@Override
	public void onApplicationEvent(ResourceCreatedEvent event) {
		Long id = event.getId();
		HttpServletResponse response = event.getResponse();
		
		fillHeaderLocation(id, response);
		
	}
	
	private void fillHeaderLocation(Long id, HttpServletResponse response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(id).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}

}
