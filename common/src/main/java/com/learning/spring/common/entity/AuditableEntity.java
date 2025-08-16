package com.learning.spring.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
public abstract class AuditableEntity extends AbstractEntity 
{
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdOn;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	@Column(updatable = false)
	private LocalDateTime updatedOn;
	
	@LastModifiedBy
	private String updatedBy;
}
