package com.learning.spring.mysqlsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @NoRepositoryBean - Tells spring not to create bean
 * @param <T>
 */
@NoRepositoryBean
public interface AbstractJPARepository<T> extends JpaRepository<T, Long> {

}
