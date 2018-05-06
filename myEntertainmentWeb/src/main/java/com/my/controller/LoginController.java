package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.Member;
import com.my.service.RegistrationService;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:4200")
public class LoginController {

	@Autowired
	private RegistrationService registrationService;


	@RequestMapping("registration")	
	public Object doRegistration(@RequestBody Member member) {

		System.out.println("Entry");
		registrationService.memberRegistration(member);

		return "Success....";


	}
	
	@RequestMapping("getall")	
	public Object doFetchAll(@RequestBody Member member) {

		
		return registrationService.getAll();



	}
	
	


}
