package com.genill.model;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserMap {

	public Object mapToUser(Users user) {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("firstName", user.getFirstName());
		userMap.put("lastName", user.getLastName());
		userMap.put("username", user.getUsername());
		userMap.put("accountCreatedDateTime", user.getAccountCreatedDateTime());
		userMap.put("email", user.getEmail());
		return userMap;
	}
}
