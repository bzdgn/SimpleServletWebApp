This is a simple project to learn how servlet's are working. Here are the notes
that I've taken while studying java servlet technology.

#mvn archetype:generate -DgroupId=com.levent -DartifactId=SimpleServletWebApp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
#http://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.1.0</version>
</dependency>

#NOT: WebServlet annotation is included with Servlet 3.0 technology !

1. Generic Servlet Class
2. HTTP Servlet Class

Every servlet implements a base interface called 'Servlet'

Servlet Intefacse is about;
	- How to configure a servlet
	- LifeCycle of servlet
		. Init			 : Initialization of the Servelt
		. Service method : Takes 2 parameters, ServletRequest and ServletResponse objects
		. Destroy		 : Destruction of the Servlet

The most important method in here is the service (Servlet.service) method because
most of this lesson is about the service.

ServletRequest object 	: Details of the incoming request, request headers, any request parameters
ServletResponse object 	: genereate the outgoing response

Servlet Interface
*****************

public interface Servlet
{
	public void init(ServletConfig);
	public void service(ServletRequest req, Servlet Response resp);
	public void destroy();
	public ServletConfig getServletConfig();
	public String getServletInfo();
}

Servlet LifeCycle
*****************
A servlet follows a certain life cycle. The servlet life cycle is managed by the servlet container.
The life cycle contains the following steps:

	1. Load Servlet Class.
	2. Create Instance of Servlet.
	3. Call the servlets init() method.
	4. Call the servlets service() method.
	5. Call the servlets destroy() method.

Step 1, 2 and 3 are executed only once, when the servlet is initially loaded.
By default the servlet is not loaded until the first request is received for it.
You can force the container to load the servlet when the container starts up though.
See web.xml Servlet Configuration for more details about that.

Step 4 is executed multiple times - once for every HTTP request to the servlet.
Step 5 is executed when the servlet container unloads the servlet.


IMPLEMENTING SERVLET INTERFACE
******************************
We should not tend to implement Servlet Interface directly, the library provides couple of
helper classes.

	1. GenericServlet Class
	2. HttpServlet Class
	
GenericServlet Class
********************
GenericServlet is protocol agnostic, it's not protocol based.

public abstract class GenericServlet implements Servlet, 
												ServletConfig, 
												java.io.Serializable
{
}

HttpServlet Class
*****************
Protocol based (protocol specific class), extends GenericServlet.

public class HttpServlet extends GenericServlet
{
	public void service(ServletRequest req, ServletResponse resp)
	{
		service( (HttpServletRequest) req, (HttpServletResponse) resp );
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp)
	{
		String verb = req.getMethod();
		
		if (verb.equals("GET"))
			doGet (req, resp);
		else if (verb.equals("POST"))
			doPost (req, resp);
		else...
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		...
	}
}

# project link: https://github.com/bzdgn/SimpleServletWebApp
# ssh link: 	git@github.com:bzdgn/SimpleServletWebApp.git
# link: 		http://localhost:8080/SimpleServletWebApp/Simple/home