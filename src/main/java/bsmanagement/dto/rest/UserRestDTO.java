package bsmanagement.dto.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

import bsmanagement.model.Address;
import bsmanagement.model.User.UserProfile;

public class UserRestDTO extends ResourceSupport{
	
	private String email;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	private String phone;
	private String taxPayerId;
	private List<Address> addressList;
	private String password;
	private boolean activationStatus;
	private UserProfile profile;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTaxPayerId() {
		return taxPayerId;
	}
	public void setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActivationStatus() {
		return activationStatus;
	}
	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	public UserProfile getProfile() {
		return profile;
	}
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRestDTO other = (UserRestDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	
	
	

}
