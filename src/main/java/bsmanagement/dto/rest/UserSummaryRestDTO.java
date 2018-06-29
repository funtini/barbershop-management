package bsmanagement.dto.rest;

public class UserSummaryRestDTO {
	
	private String email;
    private String name;
    private String profile;

    public UserSummaryRestDTO(String email, String name, String profile) {
		this.email = email;
		this.name = name;
		this.profile = profile;
	}

	public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	

}
