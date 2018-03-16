package system.dto;

public class UserLoginDTO {
	
	private String name;
	private String email;
	private String profile;
	private String message;

	
	public UserLoginDTO(String name, String email, String profile, String message) {
		this.name = name;
		this.email = email;
		this.profile = profile;
		this.message = message;

	}

	public UserLoginDTO(String message) {
		this.message = message;
		this.name = null;
		this.email = null;
		this.profile = null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
