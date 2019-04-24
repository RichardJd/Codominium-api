package com.rjd.condominium.api.repository.projection;

import java.time.LocalDateTime;

public class VisitSummary {

	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime finalDate;
	private String residentName;
	private String visitantName;
	private String cpfVisitant;

	public VisitSummary(Long id, LocalDateTime startDate, LocalDateTime finalDate, String residentName,
			String visitantName, String cpfVisitant) {
		this.id = id;
		this.startDate = startDate;
		this.finalDate = finalDate;
		this.residentName = residentName;
		this.visitantName = visitantName;
		this.cpfVisitant = cpfVisitant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDateTime finalDate) {
		this.finalDate = finalDate;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	public String getVisitantName() {
		return visitantName;
	}

	public void setVisitantName(String visitantName) {
		this.visitantName = visitantName;
	}

	public String getCpfVisitant() {
		return cpfVisitant;
	}

	public void setCpfVisitant(String cpfVisitant) {
		this.cpfVisitant = cpfVisitant;
	}
}
