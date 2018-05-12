package com.my.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.MemberDetails;
import com.my.service.RegistrationService;

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
	public Object doLogin(@RequestBody Map<String,String> credintial) {

		System.out.println("Entry"+credintial);

		registrationService.executeLogin(credintial.get("userName"), credintial.get("passWord"));

		return "Success....";


	}



}
