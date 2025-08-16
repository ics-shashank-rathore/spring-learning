package com.learning.spring.mysqlzoo.entity;

import com.learning.spring.common.entity.AuditableEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends AuditableEntity 
{
	private String line1;
	
	private String line2;
	
	private String city;
	
	private String state;
	
	private String country;
}


