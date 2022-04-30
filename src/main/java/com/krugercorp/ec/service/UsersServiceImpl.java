package com.krugercorp.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krugercorp.ec.model.Users;
import com.krugercorp.ec.repository.UsersRepository;

@Service
public class UsersServiceImpl implements IUsersService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public Users findUserByUsername(String username) {
		return usersRepository.findByUsername(username);
	}
	
	@Override
	public Users save(Users users) {
		return usersRepository.save(users);
	}

}
