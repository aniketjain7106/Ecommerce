package com.capgemini.security.payload;

import java.util.List;

import com.capgemini.security.model.ERole;
import com.capgemini.security.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String fullName;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, String fullName, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}