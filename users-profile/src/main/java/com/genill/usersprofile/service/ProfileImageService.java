package com.genill.usersprofile.service;

import com.genill.usersprofile.model.ProfilePicture;
import com.genill.usersprofile.repository.ProfileImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileImageService {

    @Autowired
    private ProfileImageRepository profileImageRepository;

    public void saveProfileImage(ProfilePicture profilePicture){
        LocalDateTime uploadDateTime = LocalDateTime.now();
        profilePicture.setUploadDateTime(uploadDateTime);
        profileImageRepository.save(profilePicture);
    }
}
