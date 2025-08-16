package com.learning.spring.mysqlzoo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.learning.spring.mysqlzoo.entity.Animal;
import com.learning.spring.mysqlzoo.request.dto.AnimalCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.AnimalUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.AnimalDTO;

/**
 * if unmappedTargetPolicy not use, so it will throw error of property not been mapped by createdOn, updatedOn
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalMapper {

	public final AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);
	
	Animal toEntity(AnimalCreateDTO animalDTO);
	
	AnimalDTO toDTO(Animal zoo);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void update(@MappingTarget Animal animal, AnimalUpdateDTO animalUpdateDTO);
}
