package com.learning.spring.mysqlsecurity.repository;

import java.util.Optional;

import com.learning.spring.mysqlsecurity.entity.User;

/**
 * Dont add @Reposotry, as it must be added only on custom repositor comcreate class implement this
 * eg custome method to fetch the data not using spring antltr 
 * 
 * 
 * Dont add it on interface
 * Spring auto use during @SpringBootApplicayion annaotation
 */
public interface UserRepository extends AbstractJPARepository<User> 
{
	Optional<User> findByUsername(String username);
}
