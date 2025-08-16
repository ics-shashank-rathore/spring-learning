package com.learning.spring.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.learning.spring.common.annotation.IdPrefix;

public class CustomIdGenerator implements IdentifierGenerator
{

	private static final long serialVersionUID = 1L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		// TODO Auto-generated method stub
		
		Class<?> entityClass = object.getClass();
		IdPrefix prefixAnnotation =  entityClass.getAnnotation(IdPrefix.class);
		if (prefixAnnotation == null) {
            throw new RuntimeException("Missing @IdPrefix annotation on " + entityClass.getName());
        }

        String prefix = prefixAnnotation.value();
        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection();
                PreparedStatement psSelect = connection.prepareStatement(
                        "SELECT next_val FROM id_generator WHERE prefix = ? FOR UPDATE");
                PreparedStatement psUpdate = connection.prepareStatement(
                        "UPDATE id_generator SET next_val = ? WHERE prefix = ?")) {

               psSelect.setString(1, prefix);
               ResultSet rs = psSelect.executeQuery();

               if (!rs.next()) {
                   throw new RuntimeException("No sequence found for prefix: " + prefix);
               }

               long currentVal = rs.getLong(1);
               long nextVal = currentVal + 1;

               psUpdate.setLong(1, nextVal);
               psUpdate.setString(2, prefix);
               psUpdate.executeUpdate();

               return prefix + currentVal;

           } catch (Exception e) {
               throw new RuntimeException("Error generating ID", e);
           }
	}

	//CREATE TABLE id_generator (
    //name VARCHAR(100) PRIMARY KEY,
    //value BIGINT
}
