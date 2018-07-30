package bsmanagement.dto.rest;

public class SalaryRestDTO {
	
	private String email;
	private String name;
	private double baseSalary;
	private double commissionSale;
	
	
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
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public double getCommissionSale() {
		return commissionSale;
	}
	public void setCommissionSale(double commissionSale) {
		this.commissionSale = commissionSale;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseSalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(commissionSale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		SalaryRestDTO other = (SalaryRestDTO) obj;
		if (Double.doubleToLongBits(baseSalary) != Double.doubleToLongBits(other.baseSalary))
			return false;
		if (Double.doubleToLongBits(commissionSale) != Double.doubleToLongBits(other.commissionSale))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	

	
}
