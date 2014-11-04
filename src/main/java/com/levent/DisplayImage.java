package com.levent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Check out ;
//
//http://www.javatpoint.com/example-to-display-image-using-servlet
//
//

@WebServlet("/whoami")
public class DisplayImage extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		//http://stackoverflow.com/a/19459180/3128926
		URL url = this.getClass().getClassLoader().getResource("/levo.jpg");
		

		response.setContentType("image/jpeg");
		ServletOutputStream out;
		out = response.getOutputStream();
		
		FileInputStream fin = new FileInputStream(url.getPath());

		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(out);
		int ch = 0;

		while ((ch = bin.read()) != -1)
		{
			bout.write(ch);
		}

		bin.close();
		fin.close();
		bout.close();
		out.close();
	}
}
