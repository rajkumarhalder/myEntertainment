package com.my.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.LoginInfo;
import com.my.model.MemberDetails;
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

		LoginInfo loginInfo=null;
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

			loginInfo=new LoginInfo();
			loginInfo.setFirstName(memberDetails.getName());
			loginInfo.setJwtToken(token);

		}

		return loginInfo;

	}
	
	
	@RequestMapping("getmember")	
	public Object getMyProfile() {
		
		
		return registrationService.getMemberByMemberId(memberId);

	}


}
