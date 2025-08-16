package com.learning.spring.mysqlzoo.request.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO 
{
	@NotNull(message = "id is required")
	private Long id;
	
	@NotEmpty(message = "line1 is required")
	@NotNull(message = "line1 is required")
	private String line1;

	private String line2;

	@NotEmpty(message = "city is required")
	@NotNull(message = "city is required")
	private String city;

	@NotEmpty(message = "state is required")
	@NotNull(message = "state is required")
	private String state;

	@NotEmpty(message = "country is required")
	@NotNull(message = "country is required")
	private String country;
}
