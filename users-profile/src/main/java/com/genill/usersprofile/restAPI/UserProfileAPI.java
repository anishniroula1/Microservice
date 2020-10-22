package com.genill.usersprofile.restAPI;

import com.genill.usersprofile.model.ProfilePicture;
import com.genill.usersprofile.service.ProfileImageService;
import com.genill.usersprofile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin()
@RestController
@RequestMapping("genill/api")
public class UserProfileAPI {

    private UserProfileService userProfileService;
    private ProfileImageService profileImageService;

    @Autowired
    public UserProfileAPI(UserProfileService userProfileService, ProfileImageService profileImageService){
        this.userProfileService = userProfileService;
        this.profileImageService = profileImageService;
    }

    @PostMapping(path = "/uploadprofileimage")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadProfilePicture(@RequestParam("image") MultipartFile Imagefile,
                                       @RequestParam("username") String username) throws IOException {

        String profileImageName = Imagefile.getOriginalFilename();

        ProfilePicture profileImage = new ProfilePicture();

        String fileFolderPath = "/Users/anishniroula/Documents/Development/DevGenill/genillFrontend/public/assets/Userpic/" + username + "/ProfilePictures/";
        File filePath = new File(fileFolderPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File file = new File(fileFolderPath, profileImageName);
        int i = 0;
        while(file.exists()) {
            file = new File(fileFolderPath, Imagefile.getOriginalFilename().replace(".", i+"."));
            profileImageName = Imagefile.getOriginalFilename().replace(".", i+".");
            i++;
        }
        file.createNewFile();
        try {
            profileImage.setProfileImageName(profileImageName);
            profileImage.setUsername(username);
            profileImage.setImageType(Imagefile.getContentType());
            Imagefile.transferTo(file);

            userProfileService.saveProfileImage(profileImage, username);
            profileImageService.saveProfileImage(profileImage);
            return profileImage.getProfileImageName();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profileImage.getProfileImageName();
    }
}

