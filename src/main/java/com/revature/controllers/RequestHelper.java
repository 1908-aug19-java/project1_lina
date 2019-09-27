package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.RequestDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {

	private ViewDelegate viewDelegate = new ViewDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	private AuthDelegate authDelegate = new AuthDelegate();
	private RequestDelegate requestDelegate = new RequestDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		String path = request.getServletPath();
		if(path.startsWith("/api")) {
			System.out.println("RH: /api/");
			//we will authenticate the token here
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
			String secondPath = path.substring(4);
			if(secondPath.startsWith("/employee")) {
				String record = secondPath.substring(10);
				System.out.println("request after /employee: "+ record);
				switch(record) {
				case "user":
					System.out.println("RH: /api/employee/user case");
					userDelegate.getUsers(request, response);
					break;
				
				case "pending":
					System.out.println("RH: /api/employee/pending case");
					requestDelegate.getEmployeePendingRequest(request, response);
					break;
				case "resolved":
					System.out.println("RH: /api/employee/resolved case");
					requestDelegate.getEmployeeResolvedRequest(request, response);
					break;	
				case "all":
					System.out.println("RH: /api/employee/resolved case");
					requestDelegate.getEmployeeAllRequest(request, response);
					break;		
				case "profile":
					userDelegate.getUsers(request, response);
				    break;
//				case "updateprofile":
//					userDelegate.updateProfile(request, response);
//					break;
//				case "logout":
//					request.getRequestDispatcher("/static/HomePage.html").forward(request, response);
				default:
					response.sendError(404,"Request Record(s)Not Found");
				}
				
			}else if(secondPath.startsWith("/manager")) {
				String record = secondPath.substring(9);
				switch(record) {
				case "user":
					userDelegate.getUsers(request, response);
					break;
//				case "pending":
//					break;
//				case "resolved":
//					break;
//				case "employees":
//					userDelegate.getUsers(request, response);
//					break;
//				case "resolve":
//					break;
//				case "requestfrom":
//					break;
//				case "logout":
//					request.getRequestDispatcher("/static/HomePage.html").forward(request, response);
//					break;
				default:
					response.sendError(404,"Request Record(s)Not Found");
				
				}
			}
			
			
		}else {
			viewDelegate.returnView(request, response);
		}
		
	}
	
    public void processPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException{ 
	
		String path = request.getServletPath();
		switch(path){
		case "/login":
			authDelegate.authenticate(request, response);
			break;
		case "/api/employee/new":
			System.out.println("RH:/api/employee/new case");
			requestDelegate.newRequest(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}

