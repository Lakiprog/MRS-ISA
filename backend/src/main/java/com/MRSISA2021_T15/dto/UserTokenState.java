package com.MRSISA2021_T15.dto;

public class UserTokenState {

	private String accessToken;
    private Long expiresIn;
    private Integer id;
    private String username;
    private String role;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.id = null;
        this.username = null;
        this.role = null;
    }

    public UserTokenState(String accessToken, long expiresIn, Integer id, String username, String role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
