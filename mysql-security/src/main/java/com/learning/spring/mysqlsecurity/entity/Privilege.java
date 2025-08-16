package com.learning.spring.mysqlsecurity.entity;

import com.learning.spring.common.entity.CreateOnlyAbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "privileges")
public class Privilege extends CreateOnlyAbstractEntity
{
	private String name;
}
