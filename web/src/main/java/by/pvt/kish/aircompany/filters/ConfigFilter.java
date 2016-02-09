/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.pvt.kish.aircompany.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр устанавливает кодировку UTF-8
 *
 * @author Kish Alexey
 */
public class ConfigFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}
}
