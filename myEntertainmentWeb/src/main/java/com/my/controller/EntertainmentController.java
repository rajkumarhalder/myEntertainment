package com.my.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.dto.LoginInfo;
import com.my.dto.ResponeDto;
import com.my.dto.UserToken;
import com.my.model.Expenditure;
import com.my.model.MemberDetails;
import com.my.model.Payments;
import com.my.service.EntertainmentService;
import com.my.util.EntertainmentConstant;
import com.my.util.EntertainmentUtil;
import com.my.util.TokenUtil;

@RestController
@RequestMapping("/home")

@SuppressWarnings({"rawtypes"})
public class EntertainmentController {

	@Autowired
	private EntertainmentService entertainmentService;

	@RequestMapping("healthCheck")	
	public String doHealthCheck(HttpServletRequest request,
			                        HttpServletResponse respone) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			return "Entertainment Running...";
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
			return null;
		}

	}
	
	@RequestMapping("login")	
	public Object doLogin(HttpServletRequest request,
			              HttpServletResponse respone,
			              @RequestBody Map<String,String> credintial) {

		
		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			
			List<String> errorMsgList=new ArrayList<>();
			MemberDetails memberDetails=entertainmentService.executeLogin(credintial.get("userName"), credintial.get("passWord"), errorMsgList);
			if(errorMsgList.isEmpty()) {
				String token=TokenUtil.createToken(memberDetails.getUsername(),memberDetails.getName(),memberDetails.getId());
				
				UserToken userToken=new UserToken();
				userToken.setAccessToken(token);
				userToken.setRoleId(memberDetails.getRoleId());
				
				EntertainmentUtil.generateResponse(responeDto, true, userToken, errorMsgList);
			}
			else {
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);
			}

		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}

		return responeDto;

	}




	@RequestMapping("registration")	
	public Object doRegistration(HttpServletRequest request,
			                   HttpServletResponse respone,
			                   @RequestBody MemberDetails memberDetails) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			entertainmentService.memberRegistration(memberDetails, errorMsgList, info);

			if(!errorMsgList.isEmpty()) {
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);
			}

			else {
				errorMsgList.add("Registration Successfull....");
				EntertainmentUtil.generateResponse(responeDto, true, null, errorMsgList);
			}
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}
	
	@RequestMapping("updateMyProfile")	
	public void doUpdateMyProfile(HttpServletRequest request,
			                      HttpServletResponse response,
			                      @RequestBody MemberDetails memberDetails) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			entertainmentService.updateMember(memberDetails, errorMsgList, info);
			
			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else 
				EntertainmentUtil.generateResponse(responeDto, true, null, errorMsgList);
			
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}

	}
	
	@RequestMapping("fetchMyprofile")	
	public Object doFetchMyprofile(HttpServletRequest request,
			                       HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			MemberDetails memberDetails=entertainmentService.getMemberByMemberId(info.getMemberId(), errorMsgList);

			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else
				EntertainmentUtil.generateResponse(responeDto, true, memberDetails, errorMsgList);
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

		

	}
	@RequestMapping("fetchAllMembers")	
	public Object doFetchAllMembers(HttpServletRequest request,
			                        HttpServletResponse respone) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			List list=entertainmentService.getAllMembers(errorMsgList, info);
			
			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else
				EntertainmentUtil.generateResponse(responeDto, true, list, errorMsgList);
				
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}

	
	@RequestMapping("fetchAllActiveMembers")	
	public Object doFetchAllActiveMembers(HttpServletRequest request,
			                              HttpServletResponse respone) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			List list=entertainmentService.getAllMembers(errorMsgList, info);
			
			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else
				EntertainmentUtil.generateResponse(responeDto, true, list, errorMsgList);
				
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}
	
	
	@RequestMapping("fetchPaymentInfo")	
	public Object doFetchPaymentInfo(HttpServletRequest request,
			                         HttpServletResponse response,
			                         @RequestParam("userId") Long userId,
			                         @RequestParam("year") int year) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			
			List<String> errorMsgList=new ArrayList<>();
			List list=entertainmentService.getPaymentInfo(userId, errorMsgList, year, info);
			
			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else
				EntertainmentUtil.generateResponse(responeDto, true, list, errorMsgList);
			
			
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;


	}
	


	

	@RequestMapping("getmasterdata")	
	public Object getmasterData(HttpServletRequest request,
			                    HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);

		return entertainmentService.getMonthMaster(info);

	}


	@RequestMapping("makepayment")	
	public Object makePayments(HttpServletRequest request,
			                 HttpServletResponse response,
			                 @RequestBody Payments payments) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		List<String> errorMsgList=new ArrayList<>();
		try {
			
			entertainmentService.saveOrUpdatePayment(payments, errorMsgList, info);
			
			if(!errorMsgList.isEmpty()) 
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);

			else
				EntertainmentUtil.generateResponse(responeDto, true, errorMsgList, errorMsgList);
			
			
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}

	

	@RequestMapping("getDuesPayment")	
	public Object getDuesPayments(HttpServletRequest request,
			                      HttpServletResponse response,
			                      @RequestParam("year") int year) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);

		ResponeDto responeDto=new ResponeDto();
		try {
			return entertainmentService.getDuePayments(info.getMemberId(), year, info);

		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}


	@RequestMapping("getNotification")	
	public Object getgetNotification(HttpServletRequest request,
			                         HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			return entertainmentService.getNotification(null);
			
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}

	@RequestMapping("getFundBalance")	
	public Object getFundBalance(HttpServletRequest request,
			HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			return entertainmentService.geCurrentFundBalance(info);

		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}


	@RequestMapping("updateexpanditure")	
	public void updateExpanditure(HttpServletRequest request,
			                      HttpServletResponse response,
			                      @RequestBody Expenditure expanditur) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			entertainmentService.insertExpanditur(expanditur);
			
		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}

	}

	@RequestMapping("getAllExpanditur")	
	public Object getAllExpanditur(HttpServletRequest request,
			HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			entertainmentService.getExpanditure();

		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}
		return responeDto;

	}


	@RequestMapping("sendmanualmail")	
	public void sendManualMail(HttpServletRequest request,
			HttpServletResponse response) {

		LoginInfo info=(LoginInfo) request.getAttribute(EntertainmentConstant.LOGGEDIN_USER_DETAILS);
		ResponeDto responeDto=new ResponeDto();
		try {
			List<String> errorMsgList=new ArrayList<>();
			entertainmentService.sendMail("N", info.getMemberId());

			if(!errorMsgList.isEmpty()) {
				EntertainmentUtil.generateResponse(responeDto, false, null, errorMsgList);
			}

			else {
				errorMsgList.add("Mail Sending Successfull....");
				EntertainmentUtil.generateResponse(responeDto, true, null, errorMsgList);
			}

		} catch (Exception exp) {
			EntertainmentUtil.handleException(responeDto,exp, info);
		}

	}


}
