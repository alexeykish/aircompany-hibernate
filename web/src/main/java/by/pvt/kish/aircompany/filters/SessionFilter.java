/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.pvt.kish.aircompany.filters;

import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр устанавливает кодировку UTF-8
 *
 * @author Kish Alexey
 */
public class SessionFilter implements Filter {

	private static Logger logger = Logger.getLogger(SessionFilter.class);


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		Session session = HibernateUtil.getUtil().getSession();
		chain.doFilter(request, response);
		HibernateUtil.getUtil().closeSession(session);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}
}
