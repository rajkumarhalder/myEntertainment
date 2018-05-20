package com.my.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.dto.UserToken;
import com.my.model.LoginInfo;
import com.my.model.MemberDetails;
import com.my.model.Payments;
import com.my.service.RegistrationService;
import com.my.util.EntertainmentConstant;
import com.my.util.TokenUtil;

@RestController
@RequestMapping("/")

public class LoginController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping("registration")	
	public Object doRegistration(@RequestBody MemberDetails memberDetails) {
		return registrationService.memberRegistration(memberDetails);

	}
	@RequestMapping("getall")	
	public Object doFetchAll() {
		return registrationService.getAll();

	}

	@RequestMapping("login")	
	public Object doLogin(HttpServletRequest request,
			              HttpServletResponse respone,
			              @RequestBody Map<String,String> credintial) {

		UserToken userToken=null;
		try {
			MemberDetails memberDetails=registrationService.executeLogin(credintial.get("userName"), credintial.get("passWord"));

			if(null==memberDetails) {
				try {
					respone.sendError(EntertainmentConstant.AUTHENTICATION_FAILURE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				String token=TokenUtil.createToken(memberDetails.getUserName(),memberDetails.getName(),memberDetails.getMemberId());

				userToken=new UserToken();
				userToken.setAccessToken(token);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return userToken;

	}
	
	
	@RequestMapping("getmember")	
	public Object getMyProfile(HttpServletRequest request,
							   HttpServletResponse response) {
		
		String token=request.getHeader("Authorization");
		LoginInfo loginInfo=TokenUtil.getTokendetail(request.getHeader("Authorization"));
		
		return registrationService.getMemberByMemberId(loginInfo.getMemberId());

	}
	
	
	@RequestMapping("getpayments")	
	public Object getPayments(HttpServletRequest request,
							   HttpServletResponse response) {
		
		String token=request.getHeader("Authorization");
		LoginInfo loginInfo=TokenUtil.getTokendetail(request.getHeader("Authorization"));
		
		return registrationService.getPaymentsByMemberId(loginInfo.getMemberId());

	}
	
	@RequestMapping("getmasterdata")	
	public Object getmasterData(HttpServletRequest request,
							   HttpServletResponse response) {
		
		String token=request.getHeader("Authorization");
		LoginInfo loginInfo=TokenUtil.getTokendetail(request.getHeader("Authorization"));
		
		return registrationService.getMonthMaster();

	}
	
	
	@RequestMapping("makepayment")	
	public void getPayments(HttpServletRequest request,
							   HttpServletResponse response,
							   @RequestBody Payments payments) {
		
		String token=request.getHeader("Authorization");
		LoginInfo loginInfo=TokenUtil.getTokendetail(token);
	registrationService.updatePayment(payments, loginInfo);

	}

	
	

}
