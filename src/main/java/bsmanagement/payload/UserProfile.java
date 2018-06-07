package bsmanagement.payload;

public class UserProfile {
	private String id;
    private String username;
    private String name;


    public UserProfile(String username, String name) {
    	this.id = username;
        this.username = username;
        this.name = name;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
