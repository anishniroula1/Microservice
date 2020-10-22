package com.genill.usersprofile.service;

import com.genill.usersprofile.exception.ResourceNotFoundException;
import com.genill.usersprofile.model.ProfilePicture;
import com.genill.usersprofile.model.UserProfile;
import com.genill.usersprofile.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getAllUsers() {
        return this.userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserByUsername(String username){
        return this.userProfileRepository.findById(username);
    }

    public UserProfile checkUsernameExist(String username) {
        UserProfile user = userProfileRepository.findByUsername(username);
        return user;
    }

    public UserProfile checkUserByEmail(String email) {
        UserProfile user = userProfileRepository.findByEmail(email);
        return user;
    }

    public UserProfile saveProfileImage(ProfilePicture saveImage, String username) {
        String imageName = saveImage.getProfileImageName();
        return this.userProfileRepository.findById(username).map(user -> {
            user.setProfilePicture(imageName);
            return this.userProfileRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Something went wrong "));

    }

}
