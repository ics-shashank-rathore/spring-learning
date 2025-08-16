package com.learning.spring.mysqlzoo.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO 
{
	private String username;
	
	private String password;
}
