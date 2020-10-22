package com.genill.usersprofile.repository;

import com.genill.usersprofile.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfilePicture, Long> {
}
