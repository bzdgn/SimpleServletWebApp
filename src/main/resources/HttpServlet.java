package javax.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class HttpServlet extends GenericServlet implements
		java.io.Serializable
{
	private static final String METHOD_DELETE = "DELETE";
	private static final String METHOD_HEAD = "HEAD";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_OPTIONS = "OPTIONS";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_PUT = "PUT";
	private static final String METHOD_TRACE = "TRACE";

	private static final String HEADER_IFMODSINCE = "If-Modified-Since";
	private static final String HEADER_LASTMOD = "Last-Modified";

	private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
	private static ResourceBundle lStrings = ResourceBundle
			.getBundle(LSTRING_FILE);
	
	public HttpServlet()
	{
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String protocol = req.getProtocol();
		String msg = lStrings.getString("http.method_get_not_supported");
		if (protocol.endsWith("1.1"))
		{
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
		} else
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
		}
	}
	
	protected long getLastModified(HttpServletRequest req)
	{
		return -1;
	}
	
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		NoBodyResponse response = new NoBodyResponse(resp);

		doGet(req, response);
		response.setContentLength();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String protocol = req.getProtocol();
		String msg = lStrings.getString("http.method_post_not_supported");
		if (protocol.endsWith("1.1"))
		{
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
		} else
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
		}
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String protocol = req.getProtocol();
		String msg = lStrings.getString("http.method_put_not_supported");
		if (protocol.endsWith("1.1"))
		{
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
		} else
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String protocol = req.getProtocol();
		String msg = lStrings.getString("http.method_delete_not_supported");
		if (protocol.endsWith("1.1"))
		{
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
		} else
		{
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
		}
	}

	private Method[] getAllDeclaredMethods(Class c)
	{

		if (c.equals(javax.servlet.http.HttpServlet.class))
		{
			return null;
		}

		Method[] parentMethods = getAllDeclaredMethods(c.getSuperclass());
		Method[] thisMethods = c.getDeclaredMethods();

		if ((parentMethods != null) && (parentMethods.length > 0))
		{
			Method[] allMethods = new Method[parentMethods.length
					+ thisMethods.length];
			System.arraycopy(parentMethods, 0, allMethods, 0,
					parentMethods.length);
			System.arraycopy(thisMethods, 0, allMethods, parentMethods.length,
					thisMethods.length);

			thisMethods = allMethods;
		}

		return thisMethods;
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Method[] methods = getAllDeclaredMethods(this.getClass());

		boolean ALLOW_GET = false;
		boolean ALLOW_HEAD = false;
		boolean ALLOW_POST = false;
		boolean ALLOW_PUT = false;
		boolean ALLOW_DELETE = false;
		boolean ALLOW_TRACE = true;
		boolean ALLOW_OPTIONS = true;

		for (int i = 0; i < methods.length; i++)
		{
			Method m = methods[i];

			if (m.getName().equals("doGet"))
			{
				ALLOW_GET = true;
				ALLOW_HEAD = true;
			}
			if (m.getName().equals("doPost"))
				ALLOW_POST = true;
			if (m.getName().equals("doPut"))
				ALLOW_PUT = true;
			if (m.getName().equals("doDelete"))
				ALLOW_DELETE = true;

		}

		String allow = null;
		if (ALLOW_GET)
			if (allow == null)
				allow = METHOD_GET;
		if (ALLOW_HEAD)
			if (allow == null)
				allow = METHOD_HEAD;
			else
				allow += ", " + METHOD_HEAD;
		if (ALLOW_POST)
			if (allow == null)
				allow = METHOD_POST;
			else
				allow += ", " + METHOD_POST;
		if (ALLOW_PUT)
			if (allow == null)
				allow = METHOD_PUT;
			else
				allow += ", " + METHOD_PUT;
		if (ALLOW_DELETE)
			if (allow == null)
				allow = METHOD_DELETE;
			else
				allow += ", " + METHOD_DELETE;
		if (ALLOW_TRACE)
			if (allow == null)
				allow = METHOD_TRACE;
			else
				allow += ", " + METHOD_TRACE;
		if (ALLOW_OPTIONS)
			if (allow == null)
				allow = METHOD_OPTIONS;
			else
				allow += ", " + METHOD_OPTIONS;

		resp.setHeader("Allow", allow);
	}
	
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

		int responseLength;

		String CRLF = "\r\n";
		String responseString = "TRACE " + req.getRequestURI() + " "
				+ req.getProtocol();

		Enumeration reqHeaderEnum = req.getHeaderNames();

		while (reqHeaderEnum.hasMoreElements())
		{
			String headerName = (String) reqHeaderEnum.nextElement();
			responseString += CRLF + headerName + ": "
					+ req.getHeader(headerName);
		}

		responseString += CRLF;

		responseLength = responseString.length();

		resp.setContentType("message/http");
		resp.setContentLength(responseLength);
		ServletOutputStream out = resp.getOutputStream();
		out.print(responseString);
		out.close();
		return;
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String method = req.getMethod();

		if (method.equals(METHOD_GET))
		{
			long lastModified = getLastModified(req);
			if (lastModified == -1)
			{
				// servlet doesn't support if-modified-since, no reason
				// to go through further expensive logic
				doGet(req, resp);
			} else
			{
				long ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
				if (ifModifiedSince < (lastModified / 1000 * 1000))
				{
					// If the servlet mod time is later, call doGet()
					// Round down to the nearest second for a proper compare
					// A ifModifiedSince of -1 will always be less
					maybeSetLastModified(resp, lastModified);
					doGet(req, resp);
				} else
				{
					resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				}
			}

		} else if (method.equals(METHOD_HEAD))
		{
			long lastModified = getLastModified(req);
			maybeSetLastModified(resp, lastModified);
			doHead(req, resp);

		} else if (method.equals(METHOD_POST))
		{
			doPost(req, resp);

		} else if (method.equals(METHOD_PUT))
		{
			doPut(req, resp);

		} else if (method.equals(METHOD_DELETE))
		{
			doDelete(req, resp);

		} else if (method.equals(METHOD_OPTIONS))
		{
			doOptions(req, resp);

		} else if (method.equals(METHOD_TRACE))
		{
			doTrace(req, resp);

		} else
		{
			//
			// Note that this means NO servlet supports whatever
			// method was requested, anywhere on this server.
			//

			String errMsg = lStrings.getString("http.method_not_implemented");
			Object[] errArgs = new Object[1];
			errArgs[0] = method;
			errMsg = MessageFormat.format(errMsg, errArgs);

			resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
		}
	}
	
	private void maybeSetLastModified(HttpServletResponse resp,
			long lastModified)
	{
		if (resp.containsHeader(HEADER_LASTMOD))
			return;
		if (lastModified >= 0)
			resp.setDateHeader(HEADER_LASTMOD, lastModified);
	}
	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException
	{
		HttpServletRequest request;
		HttpServletResponse response;

		try
		{
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) res;
		} catch (ClassCastException e)
		{
			throw new ServletException("non-HTTP request or response");
		}
		service(request, response);
	}
}

/*
 * A response that includes no body, for use in (dumb) "HEAD" support. This just
 * swallows that body, counting the bytes in order to set the content length
 * appropriately. All other methods delegate directly to the HTTP Servlet
 * Response object used to construct this one.
 */
// file private
class NoBodyResponse implements HttpServletResponse
{
	private HttpServletResponse resp;
	private NoBodyOutputStream noBody;
	private PrintWriter writer;
	private boolean didSetContentLength;

	// file private
	NoBodyResponse(HttpServletResponse r)
	{
		resp = r;
		noBody = new NoBodyOutputStream();
	}

	// file private
	void setContentLength()
	{
		if (!didSetContentLength)
			resp.setContentLength(noBody.getContentLength());
	}

	// SERVLET RESPONSE interface methods

	public void setContentLength(int len)
	{
		resp.setContentLength(len);
		didSetContentLength = true;
	}

	public void setCharacterEncoding(String charset)
	{
		resp.setCharacterEncoding(charset);
	}

	public void setContentType(String type)
	{
		resp.setContentType(type);
	}

	public String getContentType()
	{
		return resp.getContentType();
	}

	public ServletOutputStream getOutputStream() throws IOException
	{
		return noBody;
	}

	public String getCharacterEncoding()
	{
		return resp.getCharacterEncoding();
	}

	public PrintWriter getWriter() throws UnsupportedEncodingException
	{
		if (writer == null)
		{
			OutputStreamWriter w;

			w = new OutputStreamWriter(noBody, getCharacterEncoding());
			writer = new PrintWriter(w);
		}
		return writer;
	}

	public void setBufferSize(int size) throws IllegalStateException
	{
		resp.setBufferSize(size);
	}

	public int getBufferSize()
	{
		return resp.getBufferSize();
	}

	public void reset() throws IllegalStateException
	{
		resp.reset();
	}

	public void resetBuffer() throws IllegalStateException
	{
		resp.resetBuffer();
	}

	public boolean isCommitted()
	{
		return resp.isCommitted();
	}

	public void flushBuffer() throws IOException
	{
		resp.flushBuffer();
	}

	public void setLocale(Locale loc)
	{
		resp.setLocale(loc);
	}

	public Locale getLocale()
	{
		return resp.getLocale();
	}

	// HTTP SERVLET RESPONSE interface methods

	public void addCookie(Cookie cookie)
	{
		resp.addCookie(cookie);
	}

	public boolean containsHeader(String name)
	{
		return resp.containsHeader(name);
	}

	/** @deprecated */
	public void setStatus(int sc, String sm)
	{
		resp.setStatus(sc, sm);
	}

	public void setStatus(int sc)
	{
		resp.setStatus(sc);
	}

	public void setHeader(String name, String value)
	{
		resp.setHeader(name, value);
	}

	public void setIntHeader(String name, int value)
	{
		resp.setIntHeader(name, value);
	}

	public void setDateHeader(String name, long date)
	{
		resp.setDateHeader(name, date);
	}

	public void sendError(int sc, String msg) throws IOException
	{
		resp.sendError(sc, msg);
	}

	public void sendError(int sc) throws IOException
	{
		resp.sendError(sc);
	}

	public void sendRedirect(String location) throws IOException
	{
		resp.sendRedirect(location);
	}

	public String encodeURL(String url)
	{
		return resp.encodeURL(url);
	}

	public String encodeRedirectURL(String url)
	{
		return resp.encodeRedirectURL(url);
	}

	public void addHeader(String name, String value)
	{
		resp.addHeader(name, value);
	}

	public void addDateHeader(String name, long value)
	{
		resp.addDateHeader(name, value);
	}

	public void addIntHeader(String name, int value)
	{
		resp.addIntHeader(name, value);
	}

	/**
	 * @deprecated As of Version 2.1, replaced by
	 *             {@link HttpServletResponse#encodeURL}.
	 * 
	 */

	public String encodeUrl(String url)
	{
		return this.encodeURL(url);
	}

	/**
	 * @deprecated As of Version 2.1, replaced by
	 *             {@link HttpServletResponse#encodeRedirectURL}.
	 * 
	 */

	public String encodeRedirectUrl(String url)
	{
		return this.encodeRedirectURL(url);
	}

}

/*
 * Servlet output stream that gobbles up all its data.
 */

// file private
class NoBodyOutputStream extends ServletOutputStream
{

	private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
	private static ResourceBundle lStrings = ResourceBundle
			.getBundle(LSTRING_FILE);

	private int contentLength = 0;

	// file private
	NoBodyOutputStream()
	{
	}

	// file private
	int getContentLength()
	{
		return contentLength;
	}

	public void write(int b)
	{
		contentLength++;
	}

	public void write(byte buf[], int offset, int len) throws IOException
	{
		if (len >= 0)
		{
			contentLength += len;
		} else
		{
			// XXX
			// isn't this really an IllegalArgumentException?

			String msg = lStrings.getString("err.io.negativelength");
			throw new IOException("negative length");
		}
	}
}
