package com.rjd.condominium.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CondominiumExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getCause() != null ?  ex.getCause().toString() : ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDev));

		return handleExceptionInternal(ex, errors , headers, status, request);
	}

	public static class Error {
		
		private String messageDev;
		private String messageUser;
		
		public Error(String messageUser, String messageDev) {
			this.messageDev = messageDev;
			this.messageUser = messageUser;
		}

		public String getMessageDev() {
			return messageDev;
		}

		public String getMessageUser() {
			return messageUser;
		}
	}
}
