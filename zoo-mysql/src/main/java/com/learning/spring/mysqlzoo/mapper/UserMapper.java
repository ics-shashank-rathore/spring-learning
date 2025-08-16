package com.learning.spring.mysqlzoo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.learning.spring.mysqlsecurity.entity.User;
import com.learning.spring.mysqlzoo.response.dto.UserDTO;

/**
 * if unmappedTargetPolicy not use, so it will throw error of property not been mapped by createdOn, updatedOn
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	public final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO toDTO(User user);
}
