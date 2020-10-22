package com.genill.usersprofile.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.genill.usersprofile.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private UserProfileService userProfileService;

    @Autowired
    public Query(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    public List<UserProfile> getUsers() {
        return this.userProfileService.getAllUsers();
    }

    public Optional<UserProfile> userByUsername(String username) {
        return userProfileService.getUserByUsername(username);
    }

    public String checkUsernameExist(String username) {
        try {
            UserProfile u = userProfileService.checkUsernameExist(username);
            if (u != null) {
                return "Username is already exist";
            }
        } catch (NullPointerException e) {

        }
        return "Username is available to register";
    }

    public String checkEmailExist(String email){
        try {
            if (!email.contains("@")) {
                return "Please Enter Valid Email";
            } else if (userProfileService.checkUserByEmail(email) != null) {
                return "Email already Exist, Please login Using your email Address";
            }
        } catch(NullPointerException e) {

        }
        return "Email is available to register";
    }


}
