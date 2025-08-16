package com.learning.spring.mysqlzoo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.learning.spring.mysqlzoo.entity.Address;
import com.learning.spring.mysqlzoo.request.dto.AddressDTO;

/**
 * if unmappedTargetPolicy not use, so it will throw error of property not been mapped by createdOn, updatedOn
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

	public final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	Address toEntity(AddressDTO addressDTO);
}
