package com.my.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import com.my.dto.LoginInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenUtil {
	
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
	
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
				.signWith(SignatureAlgorithm.HS256,base64SecretBytes).compact();


		return jwt;

	}

	
	public static LoginInfo getTokendetail(String jwtToken) {
		
		LoginInfo loginInfo=null;
		try {
			
			Claims claims = Jwts.parser()
                    .setSigningKey(base64SecretBytes)
                    .parseClaimsJws(jwtToken)
                    .getBody();
			loginInfo=new LoginInfo();
			loginInfo.setFirstName((String) claims.get("firstName"));
			loginInfo.setMemberId(Long.valueOf(String.valueOf( claims.get("memberId"))));
		
		} catch (Exception e) {
			throw e;
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
