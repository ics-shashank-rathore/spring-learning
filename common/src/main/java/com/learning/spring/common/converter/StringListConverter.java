package com.learning.spring.common.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		// TODO Auto-generated method stub
		 return attribute != null ? String.join(",", attribute) : "";
	}

	@Override
	public List<String> convertToEntityAttribute(String joined) {
		// TODO Auto-generated method stub
		 return joined != null && !joined.isEmpty()
		            ? Arrays.asList(joined.split(","))
		            : new ArrayList<>();
	}

	
	
	
}
