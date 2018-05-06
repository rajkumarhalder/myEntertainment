package com.my.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class JwtFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("Filter Destroyed....");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		try {

			String token=request.getParameter(EntertainmentConstant.AUTHENTICATION_HEADER_PARAMETER);

			System.out.println("Token Value..."+token);

			filterChain.doFilter(request, response);
		}
		catch(Exception exp) {


		}





	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter Initited...");
		
	}
	
	
	

}
