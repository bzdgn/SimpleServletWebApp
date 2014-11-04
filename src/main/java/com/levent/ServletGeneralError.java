package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/generalError")
public class ServletGeneralError extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/Simple/hello

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		delayOneSecond();
		resp.getWriter().write("An Exception has Occured\n...5");
		delayOneSecond();
		resp.getWriter().write("An Exception has Occured\n...4");
		delayOneSecond();
		resp.getWriter().write("An Exception has Occured\n...3");
		delayOneSecond();
		resp.getWriter().write("An Exception has Occured\n...2");
		delayOneSecond();
		resp.getWriter().write("An Exception has Occured\n...1");

		
		resp.sendRedirect("getLost");
	}
	
	private static void delayOneSecond()
	{
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
