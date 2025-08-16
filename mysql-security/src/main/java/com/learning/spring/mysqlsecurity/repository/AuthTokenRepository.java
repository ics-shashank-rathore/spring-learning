package com.learning.spring.mysqlsecurity.repository;

import java.util.Optional;

import com.learning.spring.mysqlsecurity.entity.AuthToken;

public interface AuthTokenRepository extends AbstractJPARepository<AuthToken> 
{
	Optional<AuthToken> findByToken(String username);
}
