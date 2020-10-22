package com.genill.repository;

import com.genill.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, String> {
	

	public Users findByUsername(String username);
	
}
