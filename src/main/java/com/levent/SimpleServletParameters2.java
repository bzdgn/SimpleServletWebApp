package com.levent;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/parameters2")
public class SimpleServletParameters2 extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/parameters
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		try
		{
			this.printRequestData(req);
			
			
			if( !checkPassword(req) )
			{
				http://localhost:6666/SimpleServletWebApp/getLost
				resp.sendRedirect("getLost");
			}
			
			resp.setContentType( "text/xml" );
			resp.setDateHeader( "X-Custom-Header", new Date().getTime() );
			
			this.printResponseData(resp);
			
			if( 2==2 )
				throw ( new Exception() );
			
			resp.getWriter().write("<message>You are logged in to PARAMETERS-2</message>");
		} catch (Exception e)
		{
//			resp.sendError(resp.SC_INTERNAL_SERVER_ERROR);
			resp.sendRedirect("generalError");
		}
	}
	
	//http://localhost:6666/SimpleServletWebApp/parameters?password=kawabunga
	private static boolean checkPassword(HttpServletRequest req)
	{
		String password = "kawabunga";
		
		if ( req.getParameter("password").equals(password) )
			return true;
		else
			return false;
	}
	
	private static void printRequestData(HttpServletRequest req) throws ServletException, IOException
	{
		System.out.println("Request        : " + req.toString() );
		System.out.println("Request Header : " + req.getHeader("Host") );
		System.out.println("Content Type   : " + req.getContentType() );
		System.out.println("Parameter      : " + req.getParameter("password") );
		
		BufferedReader buffer = req.getReader();
		String lineBuffer;
		
		System.out.println("*************************************");
		System.out.println("*** Request Body                  ***");
		System.out.println("*************************************");
		while( ( lineBuffer = buffer.readLine() )!= null )
		{
			System.out.println(lineBuffer);
		}
	}
	
	private static void printResponseData(HttpServletResponse resp) throws ServletException
	{
		System.out.println("Response          : " + resp.toString() );
		System.out.println("Response Header # : " + resp.getHeaderNames().size() );
		System.out.println("Response Headers  : " + resp.getHeaderNames() );
		System.out.println("Content Type      : " + resp.getContentType() );
	}
}
