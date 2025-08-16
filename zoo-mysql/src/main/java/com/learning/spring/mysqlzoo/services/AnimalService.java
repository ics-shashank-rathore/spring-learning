package com.learning.spring.mysqlzoo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.common.criteria.Criteria;
import com.learning.spring.common.response.ApiResponse;
import com.learning.spring.common.response.ApiResponseMapper;
import com.learning.spring.mysqlsecurity.exception.AlreadyExistsException;
import com.learning.spring.mysqlzoo.criteria.AnimalFilter;
import com.learning.spring.mysqlzoo.criteria.AnimalSpecification;
import com.learning.spring.mysqlzoo.entity.Animal;
import com.learning.spring.mysqlzoo.entity.Zoo;
import com.learning.spring.mysqlzoo.mapper.AnimalMapper;
import com.learning.spring.mysqlzoo.repository.AnimalRepository;
import com.learning.spring.mysqlzoo.repository.ZooRepository;
import com.learning.spring.mysqlzoo.request.dto.AnimalCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.AnimalUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.AnimalDTO;

@Service
public class AnimalService 
{
	@Autowired
	public AnimalRepository animalRepository;
	
	@Autowired
	public ZooRepository zooRepository;

	public Animal create(AnimalCreateDTO animalDTO)
	{
		Optional<Animal> animalOptional = animalRepository.findByName(animalDTO.getName());
		if (animalOptional.isPresent()) 
	        throw new AlreadyExistsException("Animal with name '" + animalDTO.getName() + "' already exists.");
		
		Animal animal = AnimalMapper.INSTANCE.toEntity(animalDTO);
		Animal animalT = animalRepository.save(animal);
		
		// Save zoo
		if(animalDTO.getZooId() != null)
		{
			Optional<Zoo> zooOptional = zooRepository.findById(animalDTO.getZooId());
			if (zooOptional.isEmpty()) 
				 return animal;
			
			// Saved by mapping
			Zoo zoo = zooOptional.get();
			if (zoo.getAnimals() == null) 
		        zoo.setAnimals(new ArrayList<>());
			
			zoo.getAnimals().add(animalT);
			zooRepository.save(zoo);
			
			// Save by custom query
			//zooRepository.saveZooAnimal(zoo.getId(), animalT.getId());
		}
	    return animal;
	}
	
	public Animal update(AnimalUpdateDTO animalUpdateDTO)
	{
		Animal animal = animalRepository.getReferenceById(animalUpdateDTO.getId());
		AnimalMapper.INSTANCE.update(animal, animalUpdateDTO);
		return animalRepository.save(animal);
	}
	
	public ApiResponse<AnimalDTO> getById(Long id)
	{
		return ApiResponseMapper.toApiResponse(AnimalMapper.INSTANCE.toDTO(animalRepository.getReferenceById(id)));
	}
	
	public ApiResponse<Animal> search(Criteria<AnimalFilter> criteria)
	{
		AnimalSpecification animalSpecification = new AnimalSpecification(criteria);
		return ApiResponseMapper.toApiResponse(animalRepository.findAll(animalSpecification, criteria.toPageable()));
	}
	
	public ApiResponse<Map<String, String>> delete(Long id)
	{
		// Get Detail
		animalRepository.getReferenceById(id);
		animalRepository.deleteById(id);
		
		return ApiResponseMapper.toApiResponse(Map.of("message", "Successfully Deleted"));
	}
}
