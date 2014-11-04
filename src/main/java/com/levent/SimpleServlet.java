package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/home")
public class SimpleServlet extends HttpServlet
{
//	http://localhost:8080/SimpleServletWebApp/Simple/home

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		//simple string return
		//resp.getWriter().write("Naber lan");
		
		//Sample Xml Output
		String outputXmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		
		outputXmlString += "<reply><id>Naber lan</id><message>Forza FENER!</message></reply>";
		
		//Sample Json Output
		//As is;
		//String outputJsonString = "{\"id\":\"1907\",\"message\":\"En buyuk fener!\"}";
		
		//Pretty Print
		//String outputJsonString = "{\n\t\"id\":\"1907\",\n\t\"message\":\"En buyuk fener!\"\n}";
		
		resp.getWriter().write(outputXmlString);
	}
}
