package com.learning.spring.mysqlzoo.entity;

import java.util.List;

import com.learning.spring.common.converter.StringListConverter;
import com.learning.spring.common.entity.AuditableEntity;
import com.learning.spring.mysqlzoo.enumeration.AnimalCategory;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Animal extends AuditableEntity 
{
	private String name;
	
	private String aliasName; 
	
	@Enumerated(EnumType.STRING)
	private AnimalCategory category;
	
	@Convert(converter = StringListConverter.class)
	private List<String> skills;
}


