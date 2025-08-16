package com.learning.spring.mysqlzoo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.learning.spring.mysqlsecurity.repository.AbstractJPARepository;
import com.learning.spring.mysqlzoo.entity.Animal;

public interface AnimalRepository extends AbstractJPARepository<Animal>, JpaSpecificationExecutor<Animal> 
{
	Optional<Animal> findByName(String name);
}
