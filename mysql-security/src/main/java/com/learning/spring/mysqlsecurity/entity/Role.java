package com.learning.spring.mysqlsecurity.entity;

import java.util.List;

import com.learning.spring.common.entity.CreateOnlyAbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role extends CreateOnlyAbstractEntity 
{
	private String name;
	
	/**
	 * By default Lazy - Without this error could not initialize proxy - no Session
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "roles_privileges",
	  joinColumns = @JoinColumn(name = "role_id"),
	  inverseJoinColumns = @JoinColumn(name= "privilege_id")
	)
	private List<Privilege> privileges;
}
