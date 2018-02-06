package com.imooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("destroy---FirstFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("start----doFilter--FirstFilter");
		chain.doFilter(request, response);
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse response2 =(HttpServletResponse) response;
		//重定向
		response2.sendRedirect(req.getContextPath()+"/main.jsp");
		//转发
//		req.getRequestDispatcher("main.jsp").forward(request, response);
//		req.getRequestDispatcher("main.jsp").include(request, response);
		System.out.println("end------doFilter--FirstFilter");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init----FirstFilter");
	}


}
