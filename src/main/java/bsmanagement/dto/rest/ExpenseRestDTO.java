package bsmanagement.dto.rest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseRestDTO {
	
	private int expenseId;
	private String name;
	private String type;
	private double valueOfPayment;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfPayment;
	private String description;
	
	
	
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getValueOfPayment() {
		return valueOfPayment;
	}
	public void setValueOfPayment(double valueOfPayment) {
		this.valueOfPayment = valueOfPayment;
	}
	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + expenseId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valueOfPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ExpenseRestDTO other = (ExpenseRestDTO) obj;
		if (expenseId != other.expenseId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (Double.doubleToLongBits(valueOfPayment) != Double.doubleToLongBits(other.valueOfPayment))
			return false;
		return true;
	}

	
	
	
}
