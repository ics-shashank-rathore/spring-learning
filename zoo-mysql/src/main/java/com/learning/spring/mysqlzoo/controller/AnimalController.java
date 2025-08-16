package com.learning.spring.mysqlzoo.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.common.criteria.Criteria;
import com.learning.spring.common.response.ApiResponse;
import com.learning.spring.mysqlzoo.criteria.AnimalFilter;
import com.learning.spring.mysqlzoo.entity.Animal;
import com.learning.spring.mysqlzoo.entity.Zoo;
import com.learning.spring.mysqlzoo.request.dto.AnimalCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.AnimalUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.AnimalDTO;
import com.learning.spring.mysqlzoo.services.AnimalService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("animal")
@RequiredArgsConstructor
public class AnimalController 
{
	private final AnimalService animalService;
	
	@PostMapping("save")
	@PreAuthorize("hasAuthority('CAN_CREATE')")
	public ResponseEntity<Animal> save(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody AnimalCreateDTO animalDTO) 
	{
		Animal animal = animalService.create(animalDTO);
		URI location = URI.create("/animal/save/" + animal.getId());
		
		return ResponseEntity
	            .created(location) 
	            .body(animal);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_CREATE')")
	public ResponseEntity<?> update(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody AnimalUpdateDTO animalDTO) 
	{
		animalService.update(animalDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_DELETE')")
	public ResponseEntity<ApiResponse<Map<String, String>>> delete(@PathVariable @NotNull(message = "id is required") Long id) 
	{
		return ResponseEntity.ok(animalService.delete(id));	
	}
	
	@PostMapping("list")
	@PreAuthorize("hasAuthority('CAN_VIEW')")
	public ResponseEntity<ApiResponse<Animal>> list(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody Criteria<AnimalFilter> criteria) 
	{
		return ResponseEntity.ok(animalService.search(criteria));
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_VIEW')")
	public ResponseEntity<ApiResponse<AnimalDTO>> detail(@Valid 
			@NotNull(message = "requestData is required") 
			@PathVariable @NotNull(message = "id is required") Long id) 
	{
		
		return ResponseEntity.ok(animalService.getById(id));	
	}
}









