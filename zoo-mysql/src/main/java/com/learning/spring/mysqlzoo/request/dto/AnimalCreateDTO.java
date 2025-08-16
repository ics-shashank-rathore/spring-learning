package com.learning.spring.mysqlzoo.request.dto;

import java.util.List;

import com.learning.spring.mysqlzoo.enumeration.AnimalCategory;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalCreateDTO 
{
	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;

	@NotEmpty(message = "aliasName is required")
	@NotNull(message = "aliasName is required")
	private String aliasName; 

	@NotNull(message = "category is required")
	@Enumerated(EnumType.STRING)
	private AnimalCategory category;

	@NotEmpty(message = "skills is required")
	@NotNull(message = "skills is required")
	private List<String> skills;

	private Long zooId;
}
