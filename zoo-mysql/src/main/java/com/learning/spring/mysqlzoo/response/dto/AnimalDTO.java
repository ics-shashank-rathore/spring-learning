package com.learning.spring.mysqlzoo.response.dto;

import java.util.List;

import com.learning.spring.mysqlzoo.enumeration.AnimalCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDTO 
{
	private Long id;
	
	private String name;

	private String aliasName; 

	private AnimalCategory category;

	private List<String> skills;
}
