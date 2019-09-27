package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.revature.models.Department;
//import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.util.ConnectionUtil;


public class EmployeeDaoImpl implements EmployeeDao{
	
	private List<Employee> employees = new ArrayList<>();
	private DepartmentDao deptDao = new DepartmentDaoImpl();
	
	public EmployeeDaoImpl() {
		employees.add(new Employee(1, "AmilyBuffer", "happy123", "general", 5, deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(2, "BobBrighten", "secret888", "general", 5, deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(3, "DanArmstrong", "baby2011", "general",5, deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(4, "EricMorrow", "cat456", "general",5,deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(5, "JacobWalker", "fisherman11", "manager", -1, deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(6, "RosioBenson", "love33cat", "general",5, deptDao.getDepartmentByName("Marketing")));
		employees.add(new Employee(7, "WilliamColon", "super11", "general",8, deptDao.getDepartmentByName("IT")));
		employees.add(new Employee(8, "JennyColeman", "pass456", "manager", -1, deptDao.getDepartmentByName("IT")));
		employees.add(new Employee(9, "DerekCashin", "tomylover88", "general",8, deptDao.getDepartmentByName("IT")));
		employees.add(new Employee(10, "JuliaRoberts", "webhunter123", "general",8, deptDao.getDepartmentByName("IT")));
		
//		for(Employee e: employees) {
//			System.out.println(e);
//		}
	}

	public List<Employee> getAll() {
	
		return new ArrayList<>(employees);
	}

	public Employee getEmployeeById(int id) {
//		String sql="select * from \"Reimbursement\".employee where em_id = ?";
//		try(Connection c =ConnectionUtil.getHardCodeConnection();
//				PreparedStatement ps = c.prepareStatement(sql)){
//			      
//			    
//			    ps.setInt(1, e.getId());
//		       
//		        
//		        ResultSet rs = ps.executeQuery();
//			        
//				while(rs.next()) {
//					int employeeId = rs.getInt("em_id");
//					String employeeName = rs.getString("em_name");
//					String employeePassword = rs.getString("em_password");
//					String employeeTitle = rs.getString("em_title");
//					int managerId = rs.getInt("manager_id");
//                  int deptId = rs.getInt("dept_id");		
//		            Department dept = deptDao.getDepartmentById(deptId);
					
//					if(employeeId==id) {
//						Employee e = new Employee(employeeId, employeeName, employeePassword, employeeTitle, managerId, dept);
//					   
//               	}
//					
//					
//			    
//			} catch (SQLException error) {
//		
//				error.printStackTrace();
//			}
//		return ;
//	}
		for(Employee e: employees) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}


	public Employee getEmployeeByName(String username) {
		for(Employee e: employees) {
			if(e.getName() == username) {
				return e;
			}
		}
		return null;
	}
	
	public Employee getEmployeeByNameAndPassword(String username, String password) {
		//System.out.println("username from param: " + username +"password from param:" + password);
//         String sql = "select * from \"Reimbursement\".employee";
//        
//		try(Connection c =ConnectionUtil.getHardCodeConnection();
//				Statement s = c.createStatement(); ResultSet rs = s.executeQuery(sql);){
//		        
//		     
//				while(rs.next()) {
//					int employeeId = rs.getInt("em_id");
//					String employeeName = rs.getString("em_name");
//					String employeePassword = rs.getString("em_password");
//					String employeeTitle = rs.getString("request_status");
//					int managerId = rs.getInt("manager_id");
//					em.
//					if(employeeName.equals(username)&& employeePassword.equals(password)) {
						
//					}
//					Employee manager = emDao.getEmployeeById(managerId);
//					Request request = new Request(requestId, submitDate, submitFrom, requestAmount, spendCategory, requestStatus,resolvedDate, manager);
//					reqs.add(request);					
//				}
//			    
//	
//			    
//			} catch (SQLException error) {
//		
//				error.printStackTrace();
//			}
		for(Employee e: employees) {
//			System.out.println("name: "+ e.getName() + "param username:"+ username);
//			System.out.println("pass: "+ e.getPassword() + "param pass:"+ password);
//			boolean nameequal =(e.getName().equals(username));
//			boolean passequal =(e.getPassword().equals(password));
//			System.out.println("name equal?" +nameequal +" password equal? "+passequal);
			
			if(e.getName() != null && e.getName().equals(username)) {
				if(e.getPassword()!= null && e.getPassword().equals(password)) {
					 return e;
				}
			}
		}
		return null;
	}
	
	public void updateEmployee(Employee updatedEmp) {
		
		for(Employee emp: employees) {
			
			if(emp.getId() == updatedEmp.getId()) {
				emp.setName(updatedEmp.getName());
				emp.setPassword(updatedEmp.getPassword());
				emp.setTitle(updatedEmp.getTitle());
				emp.setReportTo(updatedEmp.getReportTo());
				emp.setDepartment(updatedEmp.getDepartment());
			}
		}
		
	}
	

}
