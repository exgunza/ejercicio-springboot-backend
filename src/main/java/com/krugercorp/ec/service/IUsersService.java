package com.krugercorp.ec.service;

import com.krugercorp.ec.model.Users;

public interface IUsersService {

	public Users findUserByUsername(String username);
	
	public Users save(Users users);
}
