package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper{
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String path = request.getServletPath();
		if(path.startsWith("/hello")) {
			request.getRequestDispatcher("/static/Hello.html").forward(request, response);
		  }
		
	}

}
