package com.learning.spring.common.criteria;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;

public class Criteria<T> 
{
	private int pageNumber = 0;
	private int pageSize = 10;
	
	@Getter
	@Setter
	private List<Map<String, String>> orderList;
	 
	@Getter
	@Setter
	private T filter;
	
	public PageRequest toPageable()
	{
		return PageRequest.of(pageNumber, pageSize);
	}

}
