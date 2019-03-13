package com.rjd.condominium.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private HttpServletResponse response;

	public RecursoCriadoEvent(Object source, Long id, HttpServletResponse response) {
		super(source);
		this.id = id;
		this.response = response;
	}

	public Long getId() {
		return id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

}
