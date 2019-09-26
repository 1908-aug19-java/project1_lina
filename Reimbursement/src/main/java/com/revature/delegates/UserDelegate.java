package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.DepartmentDao;
import com.revature.daos.DepartmentDaoImpl;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Department;
import com.revature.models.Employee;

public class UserDelegate {
	private EmployeeDao userDao = new EmployeeDaoImpl();
	private DepartmentDao deptDao = new DepartmentDaoImpl();
	   
	   public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		   String authToken = request.getHeader("Authorization");
		   String title = authToken.substring(authToken.indexOf(":")+1);
		   String idStr = request.getParameter("id");
		   if(idStr == null) {
			   if(title == "manager") {
				   List<Employee> users = userDao.getAll();
				   try(PrintWriter pw = response.getWriter();){
					   pw.write(new ObjectMapper().writeValueAsString(users));
				   } 
			   }
			   else {
				   response.sendError(401, "you are not authorized to veiw other employees' information!");
			   }
			   
		   }else {
			   if(idStr.matches("^\\d+$")) {
				   Employee u = userDao.getEmployeeById(Integer.parseInt(idStr));
				   if(u == null) {
					   response.sendError(404, "No user with given ID");
				   }
				   else {
					   try(PrintWriter pw = response.getWriter();){
						   pw.write(new ObjectMapper().writeValueAsString(u));
					   }
				   }
			   }
			   else {
				   response.sendError(400, "Invalid ID param");
			   }
		   }
			   
		   
	   }
	   
	   public void updateProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		   String idStr = request.getParameter("id");
		   String username = request.getParameter("username");
		   String password = request.getParameter("password");
		   String title = request.getParameter("title");
		   String managerIdStr = request.getParameter("reportTo");
		   String deptNameStr = request.getParameter("department");
		   
		   Department newDept = deptDao.getDepartmentByName(deptNameStr);
		   
		   Employee updatedEmp = new Employee(Integer.parseInt(idStr), username, password, title, Integer.parseInt(managerIdStr),newDept);
		   
		   userDao.updateEmployee(updatedEmp);		   
		   
	   }

}
