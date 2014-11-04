package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getLost")
public class ServletGetLost extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/Simple/hello

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{		
		resp.getWriter().write("Get lost foreigner!");
	}
}
