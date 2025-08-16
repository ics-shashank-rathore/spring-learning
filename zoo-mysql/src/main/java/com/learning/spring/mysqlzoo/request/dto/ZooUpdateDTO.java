package com.learning.spring.mysqlzoo.request.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZooUpdateDTO 
{
	@NotNull(message = "id is required")
	private Long id;
	
	@NotEmpty(message = "name is required")
	@NotNull(message = "name is required")
	private String name;
	
	private List<String> primaryAttraction;
	
	@Valid
	@NotNull(message = "address is required")
	private AddressDTO address;
}
