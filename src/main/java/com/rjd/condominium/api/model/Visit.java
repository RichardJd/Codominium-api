package com.rjd.condominium.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "visit")
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// @NotNull
	private LocalDateTime startDate;
	
	private LocalDateTime finalDate;
	
	// @NotNull
	private Boolean open;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_resident")
	private Resident resident;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_visitant")
	private Visitant visitant;

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

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	public Visitant getVisitant() {
		return visitant;
	}

	public void setVisitant(Visitant visitant) {
		this.visitant = visitant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit other = (Visit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
