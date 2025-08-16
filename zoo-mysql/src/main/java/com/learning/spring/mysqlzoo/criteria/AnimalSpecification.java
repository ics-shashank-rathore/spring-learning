package com.learning.spring.mysqlzoo.criteria;

import java.util.List;

import com.learning.spring.common.criteria.BaseSpecification;
import com.learning.spring.common.criteria.Criteria;
import com.learning.spring.mysqlzoo.entity.Animal;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AnimalSpecification extends BaseSpecification<Animal, AnimalFilter>
{
	private static final long serialVersionUID = 1L;
	
	public AnimalSpecification(Criteria<AnimalFilter> criteria) {
		super(criteria);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildPredicates(Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder cb,
			List<Predicate> predicates) {
		
		AnimalFilter filter = getCriteria().getFilter();
		
		// TODO Auto-generated method stub
		predicates.add(cb.equal(root.get("name"), filter.getName()));
		
	}
}
