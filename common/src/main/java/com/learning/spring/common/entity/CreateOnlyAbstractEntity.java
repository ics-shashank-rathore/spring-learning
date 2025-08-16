package com.learning.spring.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
public abstract class CreateOnlyAbstractEntity extends AbstractEntity 
{
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdOn;
	
	@CreatedBy
	private String createdBy;
}
