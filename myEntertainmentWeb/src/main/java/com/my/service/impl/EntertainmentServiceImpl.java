package com.my.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.my.dto.DuesInfoDto;
import com.my.dto.EntertainmentMailObject;
import com.my.dto.LoginInfo;
import com.my.dto.PaymentInfoDto;
import com.my.model.Expenditure;
import com.my.model.MailNotification;
import com.my.model.MemberDetails;
import com.my.model.MonthMaster;
import com.my.model.Notification;
import com.my.model.PaymentMaster;
import com.my.model.Payments;
import com.my.repository.ExpanditurRepository;
import com.my.repository.MailNotificationRepository;
import com.my.repository.MonthMasterRepository;
import com.my.repository.NotificationRepository;
import com.my.repository.PaymentMasterRepository;
import com.my.repository.PaymentRepository;
import com.my.repository.MemberRepository;
import com.my.service.EntertainmentService;
import com.my.util.EntertainmentConstant;
import com.my.util.EntertainmentUtil;
import com.my.util.MyEntertainmentMailSender;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class EntertainmentServiceImpl implements EntertainmentService{
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	MonthMasterRepository monthMasterRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	ExpanditurRepository expanditurRepository;
	
	@Autowired
	MailNotificationRepository mailNotificationRepository;
	
	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	private PaymentMasterRepository paymentMasterRepository;
	

	@Override
	public Object memberRegistration(MemberDetails member, List<String> messageList, LoginInfo info) {

		try {
			MemberDetails existingMember=getMemberByUserCode(member.getUsername());
			if(null==existingMember) {
				member.setRoleId(EntertainmentConstant.ROLE_ID_USER);
				member.setPassword(EntertainmentConstant.DEFAULT_PASSWPRD);
				member.setUserStatus(EntertainmentConstant.USER_STATUS_ACTIVE);
				member.setCreateDate(EntertainmentUtil.getCurrentDate());
				member.setCreateId(info.getMemberId());
				member.setIsActive(EntertainmentConstant.ACTIVE_RECORD);
				memberRepository.save(member);
			}
			else
				messageList.add("User Already Exist...");

		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	@Override
	public List<MemberDetails> getAllMembers(List<String> messageList, LoginInfo info) {

		List<MemberDetails> list=null;
		try {
			list=(List<MemberDetails>) memberRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);

		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	@Override
	public List<MemberDetails> getAllActiveMembers(List<String> messageList, LoginInfo info) {

		List<MemberDetails> list=null;
		try {
			list=(List<MemberDetails>) memberRepository.findByIsActiveAndUserStatus(EntertainmentConstant.ACTIVE_RECORD,EntertainmentConstant.USER_STATUS_ACTIVE);

		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	@Override
	public MemberDetails executeLogin(String userName,String passWord, List<String> errorMsgList) {
		
		MemberDetails memberDetails=null;
		try {
			List<MemberDetails> list=(List<MemberDetails>) memberRepository.findByUsernameAndIsActive(userName, EntertainmentConstant.ACTIVE_RECORD);

			if(null!=list && !list.isEmpty()) {
				memberDetails=list.get(0);
				
				if(!passWord.equals(memberDetails.getPassword()))
					errorMsgList.add("Incorrect Password..");
			}
			else
				errorMsgList.add("User does not exist..");

		} catch (Exception e) {
			throw e;
		}
		
		return memberDetails;
	}
	
	@Override
	public MemberDetails getMemberByMemberId(Long memberId, List<String> messageList) {
		
		try {
			List list=memberRepository.findByIdAndIsActive(memberId, EntertainmentConstant.ACTIVE_RECORD);

			if(null!=list && !list.isEmpty())
				return (MemberDetails) list.get(0);
			else return null;

		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public MemberDetails getMemberByUserCode(String userCode) {
		
		try {
			List list=memberRepository.findByUsernameAndIsActive(userCode, EntertainmentConstant.ACTIVE_RECORD);
		
		if(null!=list && !list.isEmpty())
			return (MemberDetails) list.get(0);
		else
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<PaymentInfoDto> getPaymentInfo(Long memberId, List<String> messageList, int year, LoginInfo info) {
		
		List<PaymentInfoDto> listOfPaymentInfoDto=null;
		try {
			
			List<MonthMaster> listOfMonth=(List<MonthMaster>) monthMasterRepository.findAll();

			Map<Long,String> monthIdVsName=listOfMonth.stream().collect(Collectors.toMap(MonthMaster :: getId, MonthMaster :: getMonth));
			
			List<PaymentMaster> listOfPaymentMaster=(List<PaymentMaster>) paymentMasterRepository.findByYearAndIsActive(year,EntertainmentConstant.ACTIVE_RECORD);

			List<Payments> listOfPayment=(List<Payments>) paymentRepository.findByUserIdAndIsActive(memberId, EntertainmentConstant.ACTIVE_RECORD);

			Map<Long,Payments> paymentsMap=new HashMap<>();
			if(null!=listOfPayment && !listOfPayment.isEmpty()) {
				for (Payments payments : listOfPayment) {
					paymentsMap.put(payments.getMonthId(), payments);
				}
			}

			if(null!=listOfPaymentMaster && !listOfPaymentMaster.isEmpty()) {
				listOfPaymentInfoDto=new ArrayList<>();
				for (PaymentMaster paymentMaster : listOfPaymentMaster) {
					
					
					PaymentInfoDto paymentInfoDto=new PaymentInfoDto();
					
					paymentInfoDto.setMonth(monthIdVsName.get(paymentMaster.getMonthId()));

					if(paymentsMap.containsKey(paymentMaster.getMonthId())) {

						Payments paymentsExisting=paymentsMap.get(paymentMaster.getMonthId());

						paymentInfoDto.setDueAmount(paymentMaster.getAmount().subtract(paymentsExisting.getAmount()));
						paymentInfoDto.setPaidAmount(paymentsExisting.getAmount());
						paymentInfoDto.setUserId(paymentsExisting.getUserId());
						if(paymentInfoDto.getDueAmount().doubleValue()==0)
							paymentInfoDto.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PAID);
						else if(paymentInfoDto.getDueAmount().doubleValue()==paymentMaster.getAmount().doubleValue())
							paymentInfoDto.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_DUE);
						else
							paymentInfoDto.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PARTIAL_PAID);

					}
					
					else {

						paymentInfoDto.setDueAmount(paymentMaster.getAmount());
						paymentInfoDto.setPaidAmount(new BigDecimal(0));
						paymentInfoDto.setUserId(memberId);
						paymentInfoDto.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_DUE);

					}
					listOfPaymentInfoDto.add(paymentInfoDto);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return listOfPaymentInfoDto;
	}
	
	
	@Override
	public List getDuePayments(Long memberId, int year, LoginInfo info) {
		
		List<DuesInfoDto> duesInfoDtos=null;
		try {
			Map<Long,List> usersVsPayments=new HashMap<>();
			List<MemberDetails> members=memberRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);
			List<PaymentMaster> listOfPaymentMaster=(List<PaymentMaster>) paymentMasterRepository.findByYearAndIsActive(year,EntertainmentConstant.ACTIVE_RECORD);
			if(null!=members && !members.isEmpty()) {
				duesInfoDtos=new ArrayList<>();
				List<Long> userIdList=new ArrayList<>();
				
				for (MemberDetails memberDetails : members) {
					userIdList.add(memberDetails.getId());
					DuesInfoDto duesInfoDto=new DuesInfoDto();
					duesInfoDto.setUserId(memberDetails.getId());
					duesInfoDto.setFullName(memberDetails.getName());
					duesInfoDtos.add(duesInfoDto);
				}
				List<Integer> paymentStatusList=new ArrayList<>();
				paymentStatusList.add(EntertainmentConstant.PAYMENT_STATUS_PARTIAL_PAID);
				paymentStatusList.add(EntertainmentConstant.PAYMENT_STATUS_DUE);
				paymentStatusList.add(EntertainmentConstant.PAYMENT_STATUS_PAID);
				List payments=paymentRepository.findByUserIdInAndPaymentStatusInAndYearAndIsActive(userIdList, paymentStatusList,year,EntertainmentConstant.ACTIVE_RECORD);
			
				if(null!=payments && !payments.isEmpty()) {
					usersVsPayments=(Map<Long, List>) payments.stream().collect(Collectors.groupingBy(Payments :: getUserId));
				}
				
				
				for (DuesInfoDto duesInfoDto : duesInfoDtos) {
					Map<Long,BigDecimal> monthVsAmout=new HashMap<>();
					
					for (PaymentMaster paymentMaster : listOfPaymentMaster) {
						monthVsAmout.put(paymentMaster.getMonthId(),paymentMaster.getAmount() );
					}
					
					if(usersVsPayments.containsKey(duesInfoDto.getUserId())) {
						
						List<Payments> paymentsList=usersVsPayments.get(duesInfoDto.getUserId());
						
						for (Payments payment : paymentsList) {
							BigDecimal duesAmt=monthVsAmout.get(payment.getMonthId());
							duesAmt=duesAmt.subtract(payment.getAmount());
							
							monthVsAmout.put(payment.getMonthId(), duesAmt);
							
						}

					}
					
					duesInfoDto.setMonthVsAmount(monthVsAmout);
					
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		return duesInfoDtos;
	}
	
	
	@Override
	public void saveOrUpdatePayment(Payments payments, List<String> errorMsgList, LoginInfo info) {
		
		try {

			List<PaymentMaster> paymentMasters=paymentMasterRepository.findByYearAndMonthIdAndIsActive(payments.getYear(), payments.getMonthId(), EntertainmentConstant.ACTIVE_RECORD);
			List<Payments> localPayments=paymentRepository.findByUserIdAndMonthIdAndYearAndIsActive(payments.getUserId(), payments.getMonthId(), payments.getYear(), EntertainmentConstant.ACTIVE_RECORD);
			
			if(null!=paymentMasters && !paymentMasters.isEmpty()) {
				
				PaymentMaster paymentMaster=paymentMasters.get(0);
				if(null!=localPayments && !localPayments.isEmpty()) {

					Payments localPayment=localPayments.get(0);

					localPayment.setUpdateDate(EntertainmentUtil.getCurrentDate());
					localPayment.setUpdateId(info.getMemberId());
					if(null==localPayment.getAmount())
						localPayment.setAmount(payments.getAmount());
					else
						localPayment.setAmount(localPayment.getAmount().add(payments.getAmount()));
					localPayment.setPaymentDate(EntertainmentUtil.getCurrentDate().getTime());
					if(localPayment.getAmount().doubleValue()==paymentMaster.getAmount().doubleValue())
						localPayment.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PAID);
					else if(localPayment.getAmount().doubleValue()<paymentMaster.getAmount().doubleValue())
						localPayment.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PARTIAL_PAID);
					else
						errorMsgList.add("Payment Amount can not be more than due amount...");
				if(errorMsgList.isEmpty())
					paymentRepository.save(localPayment);
				}
				
				else {
					payments.setCreateDate(EntertainmentUtil.getCurrentDate());
					payments.setCreateId(info.getMemberId());
					payments.setIsActive(EntertainmentConstant.ACTIVE_RECORD);
					payments.setUpdateDate(EntertainmentUtil.getCurrentDate());
					payments.setUpdateId(info.getMemberId());
					payments.setAmount(payments.getAmount());
					payments.setPaymentDate(EntertainmentUtil.getCurrentDate().getTime());
					if(payments.getAmount().doubleValue()==paymentMaster.getAmount().doubleValue())
						payments.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PAID);
					else
						payments.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PARTIAL_PAID);
					
					paymentRepository.save(payments);

				}
				
			}
		
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Override
	public List<MonthMaster> getMonthMaster(LoginInfo info) {

		List<MonthMaster> listOfMonth=null;
		try {
			listOfMonth=(List<MonthMaster>) monthMasterRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);

		} catch (Exception e) {
			throw e;
		}
		return listOfMonth;
	}
	
	
	
	@Override
	public void updateMember(MemberDetails memberDetails,List<String> messageList, LoginInfo info) {
		
		try {

			List<MemberDetails> list=(List<MemberDetails>) memberRepository.findByIdAndIsActive(memberDetails.getId(),EntertainmentConstant.ACTIVE_RECORD);

			if(null!=list && !list.isEmpty()) {
				MemberDetails existingMemberDetails =list.get(0);

				existingMemberDetails.setMobileNumber(memberDetails.getMobileNumber());
				existingMemberDetails.setDeskPhoneNumber(memberDetails.getDeskPhoneNumber());
				existingMemberDetails.setDateOfBirth(memberDetails.getDateOfBirth());
				existingMemberDetails.setPassword(memberDetails.getPassword());
				existingMemberDetails.setPersonalEmail(memberDetails.getPersonalEmail());
				existingMemberDetails.setPersonalMobileNumber(memberDetails.getPersonalMobileNumber());

				memberRepository.save(existingMemberDetails);
				
				messageList.add("User updated successfully...");
			}
			else
				messageList.add("User Does not exist...");


		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Override
	public List<Notification> getNotification(LoginInfo info) {

		List<Notification> listNotification=null;
		try {
			listNotification=(List<Notification>) notificationRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);

		} catch (Exception e) {
			throw e;
		}
		return listNotification;
	}
	
	@Override
	public BigDecimal geCurrentFundBalance(LoginInfo info) {
		
		BigDecimal totalAmount=new BigDecimal("0.0");
		
		try {
			List<Payments> allPayments=paymentRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);
			
			List<Expenditure> allExpenditure=expanditurRepository.findByIsActive(EntertainmentConstant.ACTIVE_RECORD);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalAmount;
	}

	
	
	@Override	
	public List<Expenditure> getExpanditure() {

		List<Expenditure> expanditurs=null;
		try {

			expanditurs=(List<Expenditure>) expanditurRepository.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expanditurs;
	}
	
	
	@Override
	public BigDecimal geTotalEarning() {
		
		BigDecimal totalEarn=new BigDecimal("0.0");
		try {
			List<Payments> list=(List<Payments>) paymentRepository.findAll();
			
			for (Payments payments : list) {
				totalEarn=totalEarn.add(payments.getAmount());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalEarn;
	}
	
	
	@Override
	public void insertExpanditur(Expenditure expanditur) {
		
		try {
			expanditurRepository.save(expanditur);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void sendMail(String allUserMailFlag, Long targetUserId) {
		
		List<MemberDetails> memberDetails=null;
		try {
			
			if("Y".equals(allUserMailFlag)) 
				memberDetails=(List<MemberDetails>) getAllMembers(null, null);
			
			else
			{
				 memberDetails=new ArrayList<>();
				 MemberDetails memberDtl=getMemberByMemberId(targetUserId, null);
				 memberDetails.add(memberDtl);
			}
			
			//Object obj=mailNotificationRepository.findByMailNotificationId(EntertainmentConstant.MAIL_ID_PAYMENT_DUE);
			Object obj=null;
			MailNotification mailNotification=(MailNotification) obj;
			
			
			
			EntertainmentMailObject mailObject=new EntertainmentMailObject();
			mailObject.setMemberDetails(memberDetails);
			mailObject.setMailBody(mailNotification.getMailBody());
			mailObject.setMailSubject(mailNotification.getMailSubject());
			
			MyEntertainmentMailSender mailSender=new MyEntertainmentMailSender(sender, mailObject);
			
			Thread t1 =new Thread(mailSender);  
			t1.start(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
