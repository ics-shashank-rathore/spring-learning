package com.learning.spring.mysqlzoo.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.learning.spring.common.converter.StringListConverter;
import com.learning.spring.common.entity.AuditableEntity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Zoo extends AuditableEntity 
{
	private String name;
	
	@Convert(converter = StringListConverter.class)
	private List<String> primaryAttraction;
	
	@OneToOne
	@JoinColumn(name="address_id")
	Address address;
	
	@OneToMany
	@JoinTable(
	  name = "zoo_animal_map",
	  joinColumns = @JoinColumn(name = "zoo_id"),
	  inverseJoinColumns = @JoinColumn(name = "animal_id")
	)
	@Cascade(CascadeType.REMOVE)
	private List<Animal> animals;
	
}



