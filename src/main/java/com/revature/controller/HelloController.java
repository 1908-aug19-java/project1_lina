package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class HelloController extends DefaultServlet {
	
	
      private static final long serialVersionUID = 1L;
      private RequestHelper requestHelper = new RequestHelper();
      
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	  
    	  String path = request.getServletPath();
    	  System.out.println(request.getMethod().toString()+path);
//    	  super.doGet(request, response);
    	  if(path.startsWith("/static")) {
    		  super.doGet(request, response);
    		 
    	  }else {
    		  
    		  requestHelper.processGet(request, response);
    	  }
    	  
      }
}
