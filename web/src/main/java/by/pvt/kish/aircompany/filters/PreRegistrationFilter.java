package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.enums.UserType;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class PreRegistrationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        List<UserType> userTypes = Arrays.asList(UserType.values());
        request.setAttribute(Attribute.USERTYPES_ATTRIBUTE, userTypes);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
