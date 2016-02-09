package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kish Alexey
 */
public class AdminAuthFilter implements Filter {

    static Logger logger = Logger.getLogger(AdminAuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Integer accessLevel = (Integer)session.getAttribute(Attribute.USERTYPE_ATTRIBUTE);
        if((accessLevel != null) && (accessLevel == 2)){
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.ERR_ACCESS);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Page.INDEX);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
