package com.genill.service;

import com.genill.authentication.PasswordHasher;
import com.genill.repository.UsersRepository;
import com.genill.model.Users;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private PasswordHasher passwordHasher;
	@Autowired
	private UsersRepository userRepo;
	
	public Users saveUser(String firstName, String lastName, String userName, String password, String role, String email){
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		LocalDateTime accountCreatedDateTime = LocalDateTime.now();
		
		Users user = new Users();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(userName);
		user.setPassword(hashedPassword);
		user.setSalt(saltString);
		user.setAccountCreatedDateTime(accountCreatedDateTime);
		user.setRole(role);
		user.setEmail(email);
		
		return userRepo.save(user);
		
	}
	
	public Users getUserByUsername(String username) {
		Users user = userRepo.findByUsername(username);
		return user;
	}
	
	public Users getValidUserWithPassword(String username, String password) {
		Users user = userRepo.findByUsername(username);
		if(user != null) {
			String storeSalt = user.getSalt();
			String storePassword = user.getPassword();
			String hashedPasword = passwordHasher.computeHash(password, Base64.decode(storeSalt));
			if(storePassword.equals(hashedPasword)) {
				return user;
			} else {
				return null;
			}
		} else {
            return null;
        }
	}
	
	public void changePassword(Users user, String newPassword, String username) {
		user = userRepo.findByUsername(username);
		byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));
        
        if(user != null) {
        	user.setPassword(hashedPassword);
        	user.setSalt(saltString);
        	userRepo.save(user);
        }
	}
	
	public List<Users> getAllUsers() {
		return (List<Users>) userRepo.findAll();
	}
}
