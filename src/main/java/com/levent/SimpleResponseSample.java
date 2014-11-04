package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nameGate")
public class SimpleResponseSample extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/Simple/home

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String output = "";
		String paramName;
		
		if( ( paramName = req.getParameter("name") ) != null )
		{
			output+="<html><head><title>" + paramName + " you are wellcome!</title></head>";
			output+="<body><h1>HELLO " + paramName + "</h1></body></html>";
		}
		else
		{
			output+="<html><head><title>" + "YOU ARE NOT WELLCOME!" + " you are wellcome!</title></head>";
			output+="<body><h1>" + "You have to enter a name!" + "</h1></body></html>";
		}
		
		resp.getWriter().write(output);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String output = "";
		String paramName;
		
		if( ( paramName = req.getParameter("name") ) != null )
		{
			output+="<html><head><title>" + paramName + " you are wellcome!</title></head>";
			output+="<body><h1>HELLO " + paramName + "</h1></body></html>";
		}
		else
		{
			output+="<html><head><title>" + "YOU ARE NOT WELLCOME!" + " you are wellcome!</title></head>";
			output+="<body><h1>" + "You have to enter a name!" + "</h1></body></html>";
		}
		
		resp.getWriter().write(output);
	}
}
