package com.my.util;

import java.math.BigDecimal;

public class EntertainmentConstant {

	public static final String AUTHENTICATION_HEADER_PARAMETER = "Token";
	
	public static final int AUTHENTICATION_FAILURE = 1000;

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String PAYMENT_STATUS_NA = "N/A";
	public static final int PAYMENT_STATUS_PARTIAL_PAID = 3;
	public static final int PAYMENT_STATUS_DUE = 2;
	public static final int PAYMENT_STATUS_PAID = 1;
	
	public static final String PAYMENT_STATUS_DUE_DESC = "Due";
	public static final String PAYMENT_STATUS_PAID_DESC = "Paid";

	public static final BigDecimal PAYMENT_TARGET_AMOUNT = new BigDecimal("250");

	public static final String CURRENT_YEAR = "2018";

	public static final Integer ROLE_ID_ADMIN_USER = 1;
	public static final Integer ROLE_ID_USER = 2;
	

	public static final Double TOTAL_EXPANDITURE = -9755.00;

	public static final String NA = "--";
	public static final String DEFAULT_PASSWPRD = "Pass@123";
	
	public static final Long MAIL_ID_PAYMENT_DUE= 1000L;
	
	public static final String MAIL_SUBJECTp = "Pass@123";

	public static final String LOGGEDIN_USER_DETAILS = "logedginuserdetails";

	public static final String SERVICE_HOME_PAGE = "/";
	
	public static final int JWT_FILTER_ERROR = 4000;
	public static final int CORS_FILTER_ERROR = 3000;

	public static final String LOGIN_URL = "/login";
	
	
	public static final Long NOT_LOGGEDIN_USER_ID = 0L;
	public static final String NOT_LOGGEDIN_USER_NAME = "ENTERTAINMENT";
	public static final String NOT_LOGGEDIN_USER_FIRST_NAME = "myEntertainment";
	public static final String INVALID_TOKEN_ERROR_MSG = "Token is missing or invalid";

	public static final int USER_STATUS_ACTIVE = 1;
	public static final int USER_STATUS_INACTIVE = 1;
	public static final int ACTIVE_RECORD = 1;

	public static final String REGISTRATION_URL = "/registration";
	
	
	
	

}
