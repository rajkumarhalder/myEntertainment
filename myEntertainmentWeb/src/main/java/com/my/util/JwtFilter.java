package com.my.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.my.dto.LoginInfo;

@Component
public class JwtFilter implements Filter{

	@Override
	public void destroy() {
		
		
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		try {

			String requestURL=request.getRequestURI();
			requestURL=requestURL.replace(request.getContextPath(), "");

			if(requestURL.endsWith(EntertainmentConstant.SERVICE_HOME_PAGE) || requestURL.endsWith(EntertainmentConstant.LOGIN_URL) || requestURL.endsWith(EntertainmentConstant.REGISTRATION_URL)) {
				LoginInfo loginInfo=new LoginInfo();
				loginInfo.setFirstName(EntertainmentConstant.NOT_LOGGEDIN_USER_FIRST_NAME);
				loginInfo.setMemberId(EntertainmentConstant.NOT_LOGGEDIN_USER_ID);
				loginInfo.setJwtToken(TokenUtil.createToken(EntertainmentConstant.NOT_LOGGEDIN_USER_FIRST_NAME, EntertainmentConstant.NOT_LOGGEDIN_USER_FIRST_NAME, EntertainmentConstant.NOT_LOGGEDIN_USER_ID));

				request.setAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS, loginInfo);
			}
			else {
				try {
					String token=request.getHeader("Authorization");
					LoginInfo loginInfo=TokenUtil.getTokendetail(token);
					request.setAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS, loginInfo);
					
				} catch (Exception e) {
					response.sendError(EntertainmentConstant.JWT_FILTER_ERROR,EntertainmentConstant.INVALID_TOKEN_ERROR_MSG);
				}

			}

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.sendError(EntertainmentConstant.JWT_FILTER_ERROR,EntertainmentConstant.INVALID_TOKEN_ERROR_MSG);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
