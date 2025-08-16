package com.learning.spring.common.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

public abstract class BaseSpecification<T, F> implements Specification<T> 
{
	private static final long serialVersionUID = 1L;

	@Getter
	private final Criteria<F> criteria;

	public BaseSpecification(Criteria<F> criteria) {
		this.criteria = criteria;
	}

	protected abstract void buildPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates);

	/**
	 * CriteriaQuery - orderBy, distinct, groupBy
	 * 
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		buildPredicates(root, query, criteriaBuilder, predicates);
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
