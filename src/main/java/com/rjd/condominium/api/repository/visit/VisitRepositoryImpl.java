package com.rjd.condominium.api.repository.visit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.rjd.condominium.api.model.Resident;
import com.rjd.condominium.api.model.Resident_;
import com.rjd.condominium.api.model.Visit;
import com.rjd.condominium.api.model.Visit_;
import com.rjd.condominium.api.model.Visitant;
import com.rjd.condominium.api.model.Visitant_;
import com.rjd.condominium.api.repository.filter.VisitFilter;
import com.rjd.condominium.api.repository.projection.VisitSummary;

public class VisitRepositoryImpl implements VisitRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Visit> filtrate(VisitFilter visitFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Visit> criteria = builder.createQuery(Visit.class);
		
		Root<Visit> root = criteria.from(Visit.class);
		Join<Visit, Resident> resident = root.join(Visit_.RESIDENT);
		Join<Visit, Visitant> visitant = root.join(Visit_.VISITANT);
		
		Predicate[] predicates = createRestrictions(visitFilter, builder, root, resident, visitant);
		criteria.where(predicates);
		
		TypedQuery<Visit> query = manager.createQuery(criteria);
		addPagingRestrictions(query, pageable);
		
		return new PageImpl<Visit>(query.getResultList(), pageable, total(visitFilter));
	}

	@Override
	public Page<VisitSummary> summarise(VisitFilter visitFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<VisitSummary> criteria = builder.createQuery(VisitSummary.class);
		
		Root<Visit> root = criteria.from(Visit.class);
		Join<Visit, Resident> resident = root.join(Visit_.RESIDENT);
		Join<Visit, Visitant> visitant = root.join(Visit_.VISITANT);
		
		criteria.select(builder.construct(VisitSummary.class
				, root.get(Visit_.id), root.get(Visit_.startDate)
				, root.get(Visit_.finalDate), root.get(Visit_.resident).get(Resident_.name)
				, root.get(Visit_.visitant).get(Visitant_.name)
				, root.get(Visit_.visitant).get(Visitant_.cpf)));
		
		Predicate[] predicates = createRestrictions(visitFilter, builder, root, resident, visitant);
		criteria.where(predicates);
		
		TypedQuery<VisitSummary> query = manager.createQuery(criteria);
		addPagingRestrictions(query, pageable);
		
		return new PageImpl<VisitSummary>(query.getResultList(), pageable, total(visitFilter));
	}

	private Predicate[] createRestrictions(VisitFilter visitFilter, CriteriaBuilder builder, Root<Visit> root, 
			Join<Visit, Resident> resident, Join<Visit, Visitant> visitant) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(visitFilter.getResidentName())) {
			predicates.add(builder.like(
					builder.lower(resident.get(Resident_.name)), 
					"%" + visitFilter.getResidentName() + "%"));
		}
		
		if (!StringUtils.isEmpty(visitFilter.getVisitantName())) {
			predicates.add(builder.like(
					builder.lower(visitant.get(Visitant_.name)), 
					"%" + visitFilter.getVisitantName() + "%"));
		}
		
		if (visitFilter.getStartDate() != null) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.get(Visit_.startDate), visitFilter.getStartDate()));
		}
		
		if (visitFilter.getFinalDate() != null) {
			predicates.add(builder.lessThanOrEqualTo(
					root.get(Visit_.finalDate), visitFilter.getFinalDate()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}	

	private void addPagingRestrictions(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstPageRegistration = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstPageRegistration);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private Long total(VisitFilter visitFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Visit> root = criteria.from(Visit.class);
		Join<Visit, Resident> resident = root.join(Visit_.RESIDENT);
		Join<Visit, Visitant> visitant = root.join(Visit_.VISITANT);
		
		Predicate[] predicates = createRestrictions(visitFilter, builder, root, resident, visitant);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
}
