package com.learning.spring.mysqlzoo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.learning.spring.mysqlzoo.entity.Zoo;
import com.learning.spring.mysqlzoo.request.dto.ZooCreateDTO;
import com.learning.spring.mysqlzoo.request.dto.ZooUpdateDTO;
import com.learning.spring.mysqlzoo.response.dto.ZooDTO;

/**
 * Delegate AddressMapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AddressMapper.class)
public interface ZooMapper {

	public final ZooMapper INSTANCE = Mappers.getMapper(ZooMapper.class);
	
	Zoo toEntity(ZooCreateDTO zoo);
	
	ZooDTO toDTO(Zoo zoo);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Zoo update(@MappingTarget Zoo zoo, ZooUpdateDTO zooDTO);
}
