package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;


import com.revature.models.Employee;


public class AuthDelegate {
	private EmployeeDao userDao = new EmployeeDaoImpl();
	   
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		//check to see if there is an auth header
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			//if the token is formatted the way we expect, we can take id from it and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String titleStr = tokenArr[1];
				if(idStr.matches("^\\d+$")){
					//check to see if there is a valid user and if that user is the appropriate role in the token
					Employee u = userDao.getEmployeeById(Integer.parseInt(idStr));
					if( u != null && u.getTitle().equals(titleStr)) {
						return true;
					}
					
			    }
		    }
	   }
		System.out.println(authToken);
		return false;
	}
	
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println("username got from request:"+username);
//		System.out.println("password got from request:"+password);
		
		Employee u = userDao.getEmployeeByNameAndPassword(username, password);
		System.out.println(u);
		
		if(u != null) {
			String token = u.getId() + ":" + u.getTitle();
			response.setHeader("Authorization", token);
//			System.out.println(token);
			response.setStatus(200);			
		}else {
			System.out.println("the authentication is failed");
			response.sendError(401);
		}
	}
	

}
