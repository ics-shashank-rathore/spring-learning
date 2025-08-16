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
import com.learning.spring.mysqlzoo.criteria.ZooFilter;
import com.learning.spring.mysqlzoo.entity.Zoo;
import com.learning.spring.mysqlzoo.request.dto.ZooCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.ZooUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.ZooDTO;
import com.learning.spring.mysqlzoo.services.ZooService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("zoo")
@RequiredArgsConstructor
public class ZooController 
{
	private final ZooService zooService;
	
	/**
	 * @RequestParam - /greet?name=Alice (application/x-www-form-urlencoded)
	 * @PathVariable - /greet/{id}
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	@PreAuthorize("hasAuthority('CAN_CREATE')")
	public ResponseEntity<Zoo> save(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody ZooCreateDTO zooCreateDTO) 
	{
		Zoo zoo = zooService.create(zooCreateDTO);
		URI location = URI.create("/zoo/save/" + zoo.getId());
		
		return ResponseEntity
	            .created(location) 
	            .body(zoo);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_CREATE')")
	public ResponseEntity<?> update(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody ZooUpdateDTO zooCreateDTO) 
	{
		zooService.update(zooCreateDTO);
		return ResponseEntity.noContent().build(); 
	}
	
	@PostMapping("list")
	@PreAuthorize("hasAuthority('CAN_VIEW')")
	public ResponseEntity<ApiResponse<Zoo>> list(@Valid 
			@NotNull(message = "requestData is required") 
			@RequestBody Criteria<ZooFilter> zooCriteria) 
	{
		return ResponseEntity.ok(zooService.search(zooCriteria));
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_VIEW')")
	public ResponseEntity<ApiResponse<ZooDTO>> detail(@PathVariable 
			@NotNull(message = "id is required") Long id) 
	{
		return ResponseEntity.ok(zooService.getById(id));
	}
	
	/**
	 * Can also used 204 No Content
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('CAN_DELETE')")
	public ResponseEntity<ApiResponse<Map<String, String>>> delete(@PathVariable 
			@NotNull(message = "id is required") Long id) 
	{
		return ResponseEntity.ok(zooService.delete(id));
	}
	
}

// Sat
// Criteria Builder +  Paginable -> Soritng
// Advice
// Spring Security


// Monday, Tuesday, 
// animal ->save, edit, delete, transfer