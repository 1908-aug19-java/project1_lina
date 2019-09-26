package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;

public class DepartmentDaoImpl implements DepartmentDao{
	private List<Department> departments = new ArrayList<>();

	public DepartmentDaoImpl() {
		departments.add(new Department(1, "Marketing"));
		departments.add(new Department(2, "IT"));
		
	}

	@Override
	public List<Department> getAll() {
		return new ArrayList<>(departments);
	}

	@Override
	public Department getDepartmentById(int id) {
		for(Department dept: departments) {
			if(dept.getId() == id) {
				return dept;
			}
		}
		return null;
	}

	@Override
	public Department getDepartmentByName(String deptName) {
		for(Department dept: departments) {
			if(dept.getName() == deptName) {
				return dept;
			}
		}
		return null;
	}

}
