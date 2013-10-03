package com.muridke.shufa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request,response);
	}
	public void processRequest(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
	
			writer.print("<p>"+request.getParameter("code")+"</p>");
			writer.close();

	}

}