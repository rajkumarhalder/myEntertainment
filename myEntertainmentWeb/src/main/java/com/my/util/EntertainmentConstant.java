package com.my.util;

import java.math.BigDecimal;

public class EntertainmentConstant {

	public static final String AUTHENTICATION_HEADER_PARAMETER = "Token";
	
	public static final int AUTHENTICATION_FAILURE = 1000;

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String PAYMENT_STATUS_NA = "N/A";
	public static final String PAYMENT_STATUS_DUE = "Due";
	public static final String PAYMENT_STATUS_PAID = "Paid";

	public static final BigDecimal PAYMENT_TARGET_AMOUNT = new BigDecimal("250");

	public static final String CURRENT_YEAR = "2018";

	public static final Integer ROLE_ID_ADMIN_USER = 1;
	public static final Integer ROLE_ID_USER = 2;
	

	public static final Double TOTAL_EXPANDITURE = -9755.00;

	public static final String NA = "--";
	public static final String DEFAULT_PASSWPRD = "Pass@123";
	
	public static final Long MAIL_ID_PAYMENT_DUE= 1000L;
	
	public static final String MAIL_SUBJECTp = "Pass@123";
	

}
