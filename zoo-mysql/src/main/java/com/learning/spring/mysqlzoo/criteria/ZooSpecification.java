package com.learning.spring.mysqlzoo.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.learning.spring.common.criteria.BaseSpecification;
import com.learning.spring.common.criteria.Criteria;
import com.learning.spring.mysqlzoo.entity.Animal;
import com.learning.spring.mysqlzoo.entity.Zoo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ZooSpecification extends BaseSpecification<Zoo, ZooFilter>
{
	private static final long serialVersionUID = 1L;

	public ZooSpecification(Criteria<ZooFilter> zooCriteria) {
		super(zooCriteria);
		// TODO Auto-generated constructor stub
	}

	/**
	 * query -> groupBy, orderBy, distinct
	 */
	@Override
	protected void buildPredicates(Root<Zoo> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates) {
		// TODO Auto-generated method stub
		ZooFilter filter = getCriteria().getFilter();
		
		predicates.add(cb.equal(root.get("name"), filter.getName()));
		
		// Join
		Join<Zoo, Animal> animalJoins = root.join("animals", JoinType.LEFT);
		if(filter.getAnimalName() != null)
			predicates.add(cb.equal(animalJoins.get("name"), filter.getAnimalName()));
		
		// Order By
		if(!CollectionUtils.isEmpty(getCriteria().getOrderList()))	
		{
			List<jakarta.persistence.criteria.Order> orders = new ArrayList<>();
			for(Map<String, String> orderMap : getCriteria().getOrderList())
			{
				for (Map.Entry<String, String> entry : orderMap.entrySet()) {
	                if ("DESC".equalsIgnoreCase(entry.getValue())) {
	                    orders.add(cb.desc(root.get(entry.getKey())));
	                } else {
	                    orders.add(cb.asc(root.get(entry.getKey())));
	                }
	            }
			}
			
			//  query.groupBy(root.get("city"));
			query.orderBy(orders);
		}
		
	}
}
