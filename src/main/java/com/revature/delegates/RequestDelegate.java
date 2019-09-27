package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.RequestDao;
import com.revature.daos.RequestDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Request;


public class RequestDelegate {
	
	private RequestDao requestDao = new RequestDaoImpl();
	private EmployeeDao userDao = new EmployeeDaoImpl();
	
		
	public void newRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Date submitDate = Date.valueOf(request.getParameter("requestTime"));
		int submitFrom = Integer.parseInt(request.getParameter("submitFrom"));
		double requestAmount = Double.parseDouble(request.getParameter("requestAmount"));
		String spendCategory = request.getParameter("requestCategory");
		
		boolean newRequestMade = requestDao.newRequest(submitDate, submitFrom, requestAmount, spendCategory);
		if(newRequestMade) {
			System.out.println("FROM REQUESTDELEGATE: the new request has been submit successfully");
			response.setStatus(200);
			
		}else {
			response.sendError(406);
			System.out.println("ERROR 406: issue with make new request ");
		}
		
	}
	
	public void getEmployeePendingRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			//if the token is formatted the way we expect, we can take id from it and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String titleStr = tokenArr[1];
				if(titleStr.equals("general")) {
					List<Request> requests = new ArrayList<>();
					String employeeName = userDao.getEmployeeById(Integer.parseInt(idStr)).getName();
					requests = requestDao.getRequestByEmplyeeNameAndStatus(employeeName, "pending");
					System.out.println(requests);
					if(requests != null) {
						System.out.println("get pending request successfully");
						try (PrintWriter pw = response.getWriter()){
							pw.write(new ObjectMapper().writeValueAsString(requests));
						}
					}else {
						System.out.println("issue with get pending requests in requestdelegate");
					}
					
				}
		    }
	   }
		
		
		
		
	}
	
	public void getEmployeeResolvedRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			//if the token is formatted the way we expect, we can take id from it and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String titleStr = tokenArr[1];
				if(titleStr.equals("general")) {
					List<Request> requests = new ArrayList<>();
					String employeeName = userDao.getEmployeeById(Integer.parseInt(idStr)).getName();
					requests = requestDao.getRequestByEmplyeeNameAndStatus(employeeName, "approved");
					System.out.println(requests);
					if(requests != null) {
						System.out.println("get approved request successfully");
						try (PrintWriter pw = response.getWriter()){
							pw.write(new ObjectMapper().writeValueAsString(requests));
						}
					}else {
						System.out.println("issue with get pending requests in requestdelegate");
					}
					
				}
		    }
	   }
	}
	//get all employee requests
public void getEmployeeAllRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			//if the token is formatted the way we expect, we can take id from it and query for that user
			if(tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String titleStr = tokenArr[1];
				if(titleStr.equals("general")) {
					List<Request> requests = new ArrayList<>();
					String employeeName = userDao.getEmployeeById(Integer.parseInt(idStr)).getName();
					requests = requestDao.getRequestByEmplyeeName(employeeName);
					System.out.println(requests);
					if(requests != null) {
						System.out.println("get approved request successfully");
						try (PrintWriter pw = response.getWriter()){
							pw.write(new ObjectMapper().writeValueAsString(requests));
						}
					}else {
						System.out.println("issue with get pending requests in requestdelegate");
					}
					
				}else {
					System.out.println("the wrong title!");
				}
		    }
	   }
	}
	public void getManagerPendingRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
	}  
	
	public void getManagerResolvedRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
	}
	
	//manager resolve some pending request
	public void updatePendingRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String idStr = request.getParameter("id");
		String requestTimeStr = request.getParameter("requestTime");
		String employeeIdStr = request.getParameter("submitFrom");
		String requestAmountStr = request.getParameter("requestAmount");
		String spendCategory = request.getParameter("spendCategory");
		String status = request.getParameter("status");
		String resolveDateStr = request.getParameter("resolveDate");
		String managerIdStr = request.getParameter("approvedBy");
		
		Employee submitFrom = userDao.getEmployeeById(Integer.parseInt(employeeIdStr));
		Employee approvedBy = userDao.getEmployeeById(Integer.parseInt(managerIdStr));
		
//		Date submitTime = requestDao.stringToDate(requestTimeStr);
//		Date resolveDate = requestDao.stringToDate(resolveDateStr);
				
//		Request updatedRequest = new Request(Integer.parseInt(idStr), submitTime, submitFrom, Double.parseDouble(requestAmountStr), spendCategory, status, resolveDate, approvedBy);
//		if(!requestDao.updateRequest(updatedRequest)) {
//			response.sendError(4004, "request record can not be found!");
//		}
		
	}
}
