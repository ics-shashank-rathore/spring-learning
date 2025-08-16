package com.learning.spring.mysqlzoo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.spring.mysqlsecurity.repository.AbstractJPARepository;
import com.learning.spring.mysqlzoo.entity.Zoo;

public interface ZooRepository extends AbstractJPARepository<Zoo>, JpaSpecificationExecutor<Zoo> 
{
	Optional<Zoo> findByName(String name);
	
	/**
	 * By default spring will treat as Select statement and throw error - TransactionRequiredException
	 */
	@Modifying
	@Query(value = "INSERT INTO zoo_animal_map (zoo_id, animal_id) VALUES (:zooId, :animalId)", nativeQuery = true)
	void saveZooAnimal(@Param("zooId") Long zooId, @Param("animalId") Long animalId); 
}
