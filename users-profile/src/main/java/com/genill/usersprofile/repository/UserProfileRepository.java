package com.genill.usersprofile.repository;

import com.genill.usersprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    public UserProfile findByUsername(String username);

    public UserProfile findByEmail(String email);
}
