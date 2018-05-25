package com.my.util;

import java.util.Date;

import com.my.model.LoginInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {
	
	public static String createToken(String memberCode,String firstName,Long memberId) {
		String jwt=null;
		Long currentMilis=new Date().getTime();
		Long tokenExpireTime=currentMilis+(1000*3600);
		
		jwt = Jwts.builder()
				.setSubject("myEntertainment")
				.setExpiration(new Date(tokenExpireTime))
				.claim("memberCode", memberCode)
				.claim("firstName", firstName)
				.claim("memberId", memberId)
				.signWith(SignatureAlgorithm.HS256,"secret").compact();


		return jwt;

	}

	
	public static LoginInfo getTokendetail(String jwtToken) {
		
		LoginInfo loginInfo=null;
		try {
			
			Claims claims = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(jwtToken)
                    .getBody();
			loginInfo=new LoginInfo();
			loginInfo.setFirstName((String) claims.get("firstName"));
			loginInfo.setMemberId(Long.valueOf(String.valueOf( claims.get("memberId"))));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginInfo;
		
	}
	
	
	public static void main(String[] args) {
		String jwt=createToken("12", "asasa", 123L);
		System.out.println(jwt);
		LoginInfo log=getTokendetail(jwt);
		
		System.out.println(log);
		
	}
}
