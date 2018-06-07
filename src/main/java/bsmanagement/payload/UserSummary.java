package bsmanagement.payload;

public class UserSummary {
	private String id;
    private String username;
    private String name;
    private String phone;

    public UserSummary(String username, String name, String phone) {
        this.username = username;
        this.name = name;
        this.id = username;
        this.phone = phone;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    

}