package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nameGate2")
public class SimpleResponseSample2 extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/Simple/home

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String output = "";
		String paramName;
		
		if
		(
			( ( paramName = req.getParameter("name") ) != null )
				&&
			( paramName != "" ) 
		)
		{
			//HTML: START
			output+="<html>";
			
			//HEAD: START
			output+="<head>";
			
			//TITLE: /
			output+="<title>" + paramName + " you are wellcome!</title>";
			
			//HEAD: END
			output+="</head>";
			
			//BODY: START
			output+="<body>";
			
			//h1
			output+="<h1>HELLO " + paramName + ", You are wellcome!" + "</h1>";
			
			//h3
			output+="<h3>" + "METHOD: \"GET\" " + "</h3>";
			
			//BODY: END
			output+="</body>";
			
			//HTML: END
			output+="</html>";
		}
		else
		{
			resp.sendRedirect("index2.jsp");
		}
		
		resp.getWriter().write(output);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String output = "";
		String paramName;
		
		if
		(
			( ( paramName = req.getParameter("name") ) != null )
				&&
			( paramName != "" ) 
		)
		{
			//HTML: START
			output+="<html>";
			
			//HEAD: START
			output+="<head>";
			
			//TITLE: /
			output+="<title>" + paramName + " you are wellcome!</title>";
			
			//HEAD: END
			output+="</head>";
			
			//BODY: START
			output+="<body>";
			
			//h1
			output+="<h1>HELLO " + paramName + ", You are wellcome!" + "</h1>";
			
			//h3
			output+="<h3>" + "METHOD: \"GET\" " + "</h3>";
			
			//BODY: END
			output+="</body>";
			
			//HTML: END
			output+="</html>";
		}
		else
		{
			resp.sendRedirect("index2.jsp");
		}
		
		resp.getWriter().write(output);
	}
}
