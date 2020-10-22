package com.genill.authentication;

import com.genill.service.UserService;
import com.genill.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class RequestAuthProvider implements AuthProvider {
	 private HttpServletRequest request;
	 private UserService userService;
	 public final static String USER_KEY = "appCurrentUser";
	 
	 @Autowired
	 public RequestAuthProvider(HttpServletRequest request, UserService userService) {
	        this.request = request;
	        this.userService = userService;
	    }

	@Override
	public boolean isLoggedIn() {
		return (request.getAttribute(USER_KEY) != null);
	}

	@Override
	public Users getCurrentUser() {
		return (Users) request.getAttribute(USER_KEY);
	}

	@Override
	public boolean signIn(String username, String password) {
		Users authenticatedUser = userService.getValidUserWithPassword(username, password);
		if(authenticatedUser != null) {
            request.setAttribute(USER_KEY, authenticatedUser);
            return true;
        } else {
            return false;
        }
	}

	@Override
	public void logOff() {
		request.removeAttribute(USER_KEY);
		
	}

	@Override
	public boolean changePassword(String existingPassword, String newPassword, String username) {
		Users userFromSession = (Users) request.getAttribute(USER_KEY);
        if(userFromSession == null) {
            return false;
        }
        Users userFromDb = userService.getValidUserWithPassword(userFromSession.getUsername(), existingPassword);
        if(userFromDb != null && userFromDb.getUsername().equals(userFromDb.getUsername())) {
        	userService.changePassword(userFromSession, newPassword, username);
            return true;
        } else {
            return false;
        }
	}

	@Override
	public void register(String firstName, String lastName, String userName, String password, String role,
			String email) {
		userService.saveUser(firstName, lastName, userName, password, role, email);
		
	}

	@Override
	public boolean userHasRole(String[] roles) {
		Users currentUser = getCurrentUser();
        if(currentUser != null && roles != null) {
            return Arrays.asList(roles).contains(currentUser.getRole());
        } else {
            return false;
        }
    }
}
