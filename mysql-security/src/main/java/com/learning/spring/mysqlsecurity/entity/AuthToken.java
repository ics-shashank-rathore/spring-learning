package com.learning.spring.mysqlsecurity.entity;

import java.time.LocalDateTime;

import com.learning.spring.common.entity.AuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuthToken extends AuditableEntity 
{
	private String token;
	
	@OneToOne
	private User user;
	
	private LocalDateTime expiresAt;
}
