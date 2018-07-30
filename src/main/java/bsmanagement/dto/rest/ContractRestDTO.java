package bsmanagement.dto.rest;

import java.time.LocalDate;

public class ContractRestDTO {
	
	private int id;
	private String email;
	private String name;
	private String role;
	private double baseSalary;
	private double salesCommission;
	private LocalDate startDate;
	private LocalDate closeDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public double getSalesCommission() {
		return salesCommission;
	}
	public void setSalesCommission(double salesCommission) {
		this.salesCommission = salesCommission;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseSalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((closeDate == null) ? 0 : closeDate.hashCode());
		result = prime * result + id;
		temp = Double.doubleToLongBits(salesCommission);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		ContractRestDTO other = (ContractRestDTO) obj;
		if (Double.doubleToLongBits(baseSalary) != Double.doubleToLongBits(other.baseSalary))
			return false;
		if (closeDate == null) {
			if (other.closeDate != null)
				return false;
		} else if (!closeDate.equals(other.closeDate))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(salesCommission) != Double.doubleToLongBits(other.salesCommission))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}


	
	
}
