package com.levent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Link;
//http://localhost:6666/SimpleServletWebApp/initDemoAnnotation?name=Levo
//
//
@WebServlet( urlPatterns = { "/initDemoAnnotation" }, initParams = {@WebInitParam(name = "ProductName", value = "WithAnn::LeventSoftwareApp01")} )
public class ServletInitParamAnnotations extends HttpServlet
{
	String appName = "placeholderApp";
	
	@Override
	public void init() throws ServletException
	{
		appName = getInitParameter("ProductName");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String name = req.getParameter("name");
		
		resp.setContentType("text/xml");
		
		if(name!=null && name!="")
			resp.getWriter().printf("<application><name>%s</name><product>%s</product></application>",name,appName);
		else
			resp.getWriter().write("<application><error>Name cannot be null</error></application>");
	}
}
