package bsmanagement.dto.rest;

public class ReportRestDTO {
	
	private String id;
	private int businessDays;
	private String status;
	private double profit;
	private double income;
	private double expenses;
	private double roi;
	private double salariesAmount;
	private double totalFeeAmount;
	private int numOfSales;
	

	
	public int getNumOfSales() {
		return numOfSales;
	}
	public void setNumOfSales(int numOfSales) {
		this.numOfSales = numOfSales;
	}
	public double getTotalFeeAmount() {
		return totalFeeAmount;
	}
	public void setTotalFeeAmount(double totalFeeAmount) {
		this.totalFeeAmount = totalFeeAmount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBusinessDays() {
		return businessDays;
	}
	public void setBusinessDays(int businessDays) {
		this.businessDays = businessDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getSalaries() {
		return salariesAmount;
	}
	public void setSalaries(double salaries) {
		this.salariesAmount = salaries;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + numOfSales;
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
		ReportRestDTO other = (ReportRestDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numOfSales != other.numOfSales)
			return false;
		return true;
	}
	
	

}
