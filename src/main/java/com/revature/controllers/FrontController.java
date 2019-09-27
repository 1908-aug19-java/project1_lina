package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.servlets.DefaultServlet;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = new RequestHelper();
       
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		System.out.println(request.getMethod().toString() +"to" + path);
		System.out.println("the context path: " + contextPath);
		if(path.startsWith("/static")){
			super.doGet(request, response);
		}else {
			requestHelper.processGet(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		System.out.println(request.getMethod().toString() +"to" + path);
		System.out.println("the context path: " + contextPath);
		requestHelper.processPost(request, response);
		
	}

}
