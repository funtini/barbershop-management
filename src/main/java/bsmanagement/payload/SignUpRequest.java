package bsmanagement.payload;


import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birth;

	@NotBlank
    @Size(min = 9, max = 12)
    private String phone;
	
	@NotBlank
    @Size(min = 9, max = 12)
    private String taxPayerId;
    
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getTaxPayerId() {
		return taxPayerId;
	}
	
	public void setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
	}
}
