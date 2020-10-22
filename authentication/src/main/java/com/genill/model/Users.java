package com.genill.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class Users {
	
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "firstname", nullable = false)
    private String firstName;
    
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Id
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "salt")
	private String salt;
    
    private String confirmPassword;
    
    @Column(name = "account_created_date_time")
	private LocalDateTime accountCreatedDateTime;
	
	private boolean passwordMatching;

	
	public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public LocalDateTime getAccountCreatedDateTime() {
		return accountCreatedDateTime;
	}

	public void setAccountCreatedDateTime(LocalDateTime accountCreatedDateTime) {
		this.accountCreatedDateTime = accountCreatedDateTime;
	}

	public void setPasswordMatching(boolean passwordMatching) {
		this.passwordMatching = passwordMatching;
	}
}
