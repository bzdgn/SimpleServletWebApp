package javax.servlet;

import java.io.IOException;
 
public interface RequestDispatcher {

    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException;
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException;
}








