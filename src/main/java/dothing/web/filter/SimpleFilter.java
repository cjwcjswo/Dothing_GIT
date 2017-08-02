package dothing.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * XSS 공격을 막기위한 필터
 */
public class SimpleFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Request메세지를 XSSRequestWrapper로 감싼다
		 XSSRequestWrapper xss =  new XSSRequestWrapper((HttpServletRequest)request);
		 chain.doFilter(xss, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
