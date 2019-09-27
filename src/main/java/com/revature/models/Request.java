package com.revature.models;

import java.io.Serializable;
import java.util.Date;

public class Request  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private Date requestTime;
	private Employee submitFrom;
	private double requestAmount;
	private String spendCategory;
	private String status;
	private Date resolveDate;
	private Employee approvedBy;
	
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int id, java.sql.Date requestTime, Employee submitFrom, double requestAmount, String spendCategory, String status,
			java.sql.Date resolveDate, Employee approvedBy) {
		super();
		this.requestTime = requestTime;
		this.submitFrom = submitFrom;
		this.requestAmount = requestAmount;
		this.spendCategory = spendCategory;
		this.status = status;
		this.resolveDate = resolveDate;
		this.approvedBy = approvedBy;
	}
	

	
	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Employee getSubmitFrom() {
		return submitFrom;
	}

	public void setSubmitFrom(Employee submitFrom) {
		this.submitFrom = submitFrom;
	}

	public double getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}

	public String getSpendCategory() {
		return spendCategory;
	}

	public void setSpendCategory(String spendCategory) {
		this.spendCategory = spendCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
	}

	public Employee getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(requestAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((requestTime == null) ? 0 : requestTime.hashCode());
		result = prime * result + ((resolveDate == null) ? 0 : resolveDate.hashCode());
		result = prime * result + ((spendCategory == null) ? 0 : spendCategory.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitFrom == null) ? 0 : submitFrom.hashCode());
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
		Request other = (Request) obj;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(requestAmount) != Double.doubleToLongBits(other.requestAmount))
			return false;
		if (requestTime == null) {
			if (other.requestTime != null)
				return false;
		} else if (!requestTime.equals(other.requestTime))
			return false;
		if (resolveDate == null) {
			if (other.resolveDate != null)
				return false;
		} else if (!resolveDate.equals(other.resolveDate))
			return false;
		if (spendCategory == null) {
			if (other.spendCategory != null)
				return false;
		} else if (!spendCategory.equals(other.spendCategory))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitFrom == null) {
			if (other.submitFrom != null)
				return false;
		} else if (!submitFrom.equals(other.submitFrom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", requestTime=" + requestTime + ", submitFrom=" + submitFrom + ", requestAmount="
				+ requestAmount + ", spendCategory=" + spendCategory + ", status=" + status + ", resolveDate="
				+ resolveDate + ", approvedBy=" + approvedBy + "]";
	}
	
	
	
	
	

}
