package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Employee;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.utils.RequestHandler;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Kish Alexey
 */
public class PreUpdateEmployeeFilter implements Filter {

    private static Logger logger = Logger.getLogger(PreUpdateEmployeeFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Long id = RequestHandler.getId(httpRequest, "eid");
            if (id < 0) {
                logger.error(Message.ERROR_ID_MISSING);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Page.ERROR);
                dispatcher.forward(request, response);
            }
            Employee employee = EmployeeService.getInstance().getById(id);
            request.setAttribute(Attribute.EMPLOYEE_ATTRIBUTE, employee);
            chain.doFilter(request,response);
        } catch (ServiceException e) {
            logger.error(Message.ERROR_SQL_DAO);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Page.ERROR);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
