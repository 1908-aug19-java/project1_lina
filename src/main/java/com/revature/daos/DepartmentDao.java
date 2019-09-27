package com.revature.daos;

import java.util.List;

import com.revature.models.Department;

public interface DepartmentDao {
	public List<Department> getAll();
	public Department getDepartmentById(int id);
	public Department getDepartmentByName(String deptName);

}
