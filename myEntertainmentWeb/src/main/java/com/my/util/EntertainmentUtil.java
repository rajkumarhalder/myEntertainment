package com.my.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.my.dto.LoginInfo;
import com.my.dto.ResponeDto;

public class EntertainmentUtil {

	public static int[] getCurrentMonthYear() {

		int[] arr=new int[2];
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		arr[0]=month;
		arr[1]=year;
		
		return arr;
	}
	
	
	public static Date getCurrentDate() {
		return new Date();
	}
	
	
	
	public static ResponeDto generateResponse(ResponeDto responeDto,boolean status,Object data,List<String> messageList) {
		
		try {
			responeDto.setData(data);
			responeDto.setStatus(status);
			if(!messageList.isEmpty()) {
				StringBuffer message=new StringBuffer();
				message.append("<ul>");
				for (String string : messageList) {
					message.append("<li>").append(string).append("</li>");
				}
				message.append("</ul>");
				responeDto.setMessage(message.toString());
			}

		} catch (Exception e) {
			throw e;
		}
		return responeDto;
	}
	
	
public static void handleException(ResponeDto responeDto,Throwable throwable,LoginInfo info) {
		
	try {
		responeDto.setStatus(false);
		responeDto.setMessage("Oops Something went wrong...");
		
	} catch (Throwable th) {
			th.printStackTrace();
		}
	}
	
}
