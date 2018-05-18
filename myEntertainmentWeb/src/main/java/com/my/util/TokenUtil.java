package com.my.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {
	
	public static String createToken(String memberCode,String firstName,Long memberId) {
		String jwt=null;
		try {
			
			Long currentMilis=new Date().getTime();
			Long tokenExpireTime=currentMilis+600000;
			
			jwt = Jwts.builder()
					.setSubject("myEntertainment")
					.setExpiration(new Date(tokenExpireTime))
					.claim("memberCode", memberCode)
					.claim("firstName", firstName)
					.claim("memberId", memberId)
					.signWith(SignatureAlgorithm.HS256,"secret".getBytes("UTF-8")).compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		return jwt;

	}

}
