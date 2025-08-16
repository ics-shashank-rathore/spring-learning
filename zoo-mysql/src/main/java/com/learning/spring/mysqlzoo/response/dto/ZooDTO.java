package com.learning.spring.mysqlzoo.response.dto;

import java.util.List;

import com.learning.spring.mysqlzoo.request.dto.AddressDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZooDTO 
{
	private Long id;
	
	private String name;
	
	private List<String> primaryAttraction;
	
	private AddressDTO address;
	
	private List<AnimalDTO> animals;
}
