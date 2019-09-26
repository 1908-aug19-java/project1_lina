package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	 public void returnView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		  String path = request.getServletPath();
		  switch(path) {
		  case "/login":
			  request.getRequestDispatcher("/static/HomePage.html").forward(request, response);
			  break;
		  case "/employee" :
			  System.out.println("arrive /employee url");
		  	 request.getRequestDispatcher("/static/Employee.html").forward(request, response);
		  	 break;
		  case "/manager":
			  request.getRequestDispatcher("/static/Manager.html").forward(request, response);
		  default:
			  response.sendError(404, "static resource not found");
		  }
	  }

}
