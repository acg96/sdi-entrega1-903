package com.uniovi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class UserConverter implements Converter<Long, User> {
	@Autowired
	private UsersService usersService;
	
	@Override
	public User convert(Long id) {
		return usersService.getUser(id);
	}
}
