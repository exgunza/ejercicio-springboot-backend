package com.krugercorp.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krugercorp.ec.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);
	
}
