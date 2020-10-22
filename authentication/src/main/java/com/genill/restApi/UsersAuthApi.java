package com.genill.restApi;

import com.genill.authentication.JwtTokenHandler;
import com.genill.authentication.RequestAuthProvider;
import com.genill.authentication.UnauthorizedException;
import com.genill.authentication.UserCreationException;
import com.genill.model.UserMap;
import com.genill.model.UserProfile;
import com.genill.repository.UserProfileRepository;
import com.genill.service.UserService;
import com.genill.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("genill/api")
public class UsersAuthApi {
	private RequestAuthProvider auth;
	private JwtTokenHandler tokenHandler;
	private UserService userService;
	private UserMap userMap;
	private UserProfileRepository userProfileRepository;

	@Autowired
	public UsersAuthApi(RequestAuthProvider auth, JwtTokenHandler tokenHandler,
						UserService userService,
						UserMap userMap, UserProfileRepository userProfileRepository) {
		this.auth = auth;
		this.tokenHandler = tokenHandler;
		this.userService = userService;
		this.userMap = userMap;
		this.userProfileRepository = userProfileRepository;
	}
	
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Users user) throws UnauthorizedException {

		if (auth.signIn(user.getUsername(), user.getPassword())) {
			Users currentUser = auth.getCurrentUser();
			tokenHandler.createToken(user.getUsername(), currentUser.getRole());

			return user.getUsername();

		} else {
			throw new UnauthorizedException();
		}
	}
	
	@PostMapping(path = "/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String register(@Valid @RequestBody Users user, BindingResult result) throws UserCreationException {
		if (result.hasErrors()) {
			String errorMessages = "";
			for (ObjectError error : result.getAllErrors()) {
				errorMessages += error.getDefaultMessage() + "\n";
			}
			throw new UserCreationException(errorMessages);
		}
		user.setRole("user");
//		emailService.sendSimpleMessage(user.getEmail(), user.getEmail());
		auth.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getRole(),
				user.getEmail());

		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(user.getUsername());
		userProfile.setFirstName(user.getFirstName());
		userProfile.setLastName(user.getLastName());
		userProfile.setEmail(user.getEmail());
		userProfile.setRole(user.getRole());
		userProfile.setAccountCreatedDateTime(LocalDateTime.now());
		userProfileRepository.save(userProfile);

		return user.getUsername();
	}
	
	@GetMapping("/logout") 
	public String logOut() {
		auth.logOff();
		return "logout Success";
	}
	
	@GetMapping("/username/{username}")
	public Object getUserByUsername(@PathVariable String username) {
		Users user = userService.getUserByUsername(username);
		return userMap.mapToUser(user);
	}
	

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
}
