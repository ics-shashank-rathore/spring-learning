package com.learning.spring.mysqlzoo.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.common.criteria.Criteria;
import com.learning.spring.common.response.ApiResponse;
import com.learning.spring.common.response.ApiResponseMapper;
import com.learning.spring.mysqlsecurity.exception.AlreadyExistsException;
import com.learning.spring.mysqlzoo.criteria.ZooFilter;
import com.learning.spring.mysqlzoo.criteria.ZooSpecification;
import com.learning.spring.mysqlzoo.entity.Address;
import com.learning.spring.mysqlzoo.entity.Zoo;
import com.learning.spring.mysqlzoo.mapper.AddressMapper;
import com.learning.spring.mysqlzoo.mapper.ZooMapper;
import com.learning.spring.mysqlzoo.repository.AddressRepository;
import com.learning.spring.mysqlzoo.repository.ZooRepository;
import com.learning.spring.mysqlzoo.request.dto.ZooCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.ZooUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.ZooDTO;

@Service
public class ZooService 
{
	@Autowired
	public ZooRepository zooRepository;
	
	@Autowired
	public AddressRepository addressRepository;

	public Zoo create(ZooCreateDTO zooDTO)
	{
		Optional<Zoo> zooOptional = zooRepository.findByName(zooDTO.getName());
		if (zooOptional.isPresent()) 
	        throw new AlreadyExistsException("Zoo with name '" + zooDTO.getName() + "' already exists.");
		
		Address address = AddressMapper.INSTANCE.toEntity(zooDTO.getAddress());
		addressRepository.save(address);
		
		Zoo zoo = ZooMapper.INSTANCE.toEntity(zooDTO);
		zoo.setAddress(address);
	    return zooRepository.save(zoo);
	}
	
	public Zoo update(ZooUpdateDTO zooDTO)
	{
		Zoo zooT = zooRepository.getReferenceById(zooDTO.getId());
		ZooMapper.INSTANCE.update(zooT, zooDTO);
	    return zooRepository.save(zooT);
	}
	
	public ApiResponse<Zoo> search(Criteria<ZooFilter> zooCriteria)
	{
		ZooSpecification zooSpecification = new ZooSpecification(zooCriteria);
		return ApiResponseMapper.toApiResponse(zooRepository.findAll(zooSpecification, zooCriteria.toPageable()));
	}
	
	public ApiResponse<ZooDTO> getById(Long id)
	{
		return ApiResponseMapper.toApiResponse(ZooMapper.INSTANCE.toDTO(zooRepository.getReferenceById(id)));
	}
	
	public ApiResponse<Map<String, String>> delete(Long id)
	{
		// Get Detail
		zooRepository.getReferenceById(id);
		
		zooRepository.deleteById(id);
		
		return ApiResponseMapper.toApiResponse(Map.of("message", "Successfully Deleted"));
	}
	
	
}
