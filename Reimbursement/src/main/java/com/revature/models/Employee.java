package com.revature.models;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String password;
	private String title;
	private int reportTo;
	private Department department;
	
	//constructors
	public Employee() {
		super();
		
	}

	public Employee(int id, String name, String password, String title, int managerId, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.title = title;
		this.reportTo = managerId;
		this.department = department;
	}

	public Employee(String name, String password, String title, int managerId, Department department) {
		super();
		this.name = name;
		this.password = password;
		this.title = title;
		this.reportTo = managerId;
		this.department = department;
	}
	
//	public public Employee(String name, String password, String title, int managerId, Department department) {
//		super();
//		this.name = name;
//		this.password = password;
//		this.title = title;
//		this.reportTo = manager;
//		this.department = department;
//	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReportTo() {
		return reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", title=" + title + ", reportTo="
				+ reportTo + ", department=" + department + "]";
	}
	
	//hashcode and equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + reportTo;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (reportTo != other.reportTo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
	
