package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	public List<Employee> getAll();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByName(String username);
	public Employee getEmployeeByNameAndPassword(String username, String password);
	public void updateEmployee(Employee updatedEmp);

}
