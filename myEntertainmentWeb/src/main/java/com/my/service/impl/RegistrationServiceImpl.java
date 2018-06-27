package com.my.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.SequenceDao;
import com.my.model.Expanditur;
import com.my.model.LoginInfo;
import com.my.model.MemberDetails;
import com.my.model.MonthMaster;
import com.my.model.Notification;
import com.my.model.Payments;
import com.my.repository.ExpanditurRepository;
import com.my.repository.MonthMasterRepository;
import com.my.repository.NotificationRepository;
import com.my.repository.PaymentRepository;
import com.my.repository.RegistrationRepository;
import com.my.service.RegistrationService;
import com.my.util.EntertainmentConstant;


@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	MonthMasterRepository monthMasterRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	ExpanditurRepository expanditurRepository;
	
	@Autowired
	SequenceDao sequenceDao;

	@Override
	public Object memberRegistration(Object object) {
		MemberDetails member=null;
		if(object instanceof MemberDetails)
			member=(MemberDetails) object;
		
		member.setMemberId(sequenceDao.getNextSequenceId("member_seq"));
		member.setRoleId(EntertainmentConstant.ROLE_ID_USER);
		
		registrationRepository.save(member);
		insertPayment(member.getMemberId());
		
		return "success";
	}
	
	@Override
	public Object getAll() {
		
		List<MemberDetails> list=registrationRepository.findAll();
		
		for (MemberDetails memberDetails : list) {
			memberDetails.setPayments(getPaymentsByMemberId(memberDetails.getMemberId()));
		}
		return list;
	}
	
	@Override
	public MemberDetails executeLogin(String userName,String passWord) {
		
		MemberDetails memberDetails=null;
		try {
			memberDetails=registrationRepository.findByUserNamePassword(userName, passWord);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberDetails;
	}
	
	@Override
	public MemberDetails getMemberByMemberId(Long memberId) {
		
		MemberDetails memberDetails=null;
		try {
			memberDetails=registrationRepository.findMemberByMemberId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberDetails;
	}
	
	@Override
	public List<Payments> getPaymentsByMemberId(Long memberId) {
		
		List<Payments> listPayments=null;
		List<Payments> listPaymentsFinal=null;
		try {
			listPaymentsFinal=new ArrayList<>();
			int currentMonthId=new Date().getMonth()+1;
			listPayments=paymentRepository.getPaymentsByMember(memberId);
			
			for (Payments payments : listPayments) {
				if(payments.getMonthId()<=currentMonthId)
					listPaymentsFinal.add(payments);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPaymentsFinal;
	}
	
	
	@Override
	public List<Payments> getDuePayments(Long memberId) {
		
		List<Payments> listPayments=null;
		try {
			
			int currentMonthId=new Date().getMonth()+1;
			Long monthid=new Long(currentMonthId);
			listPayments=paymentRepository.getPaymentsdues(memberId, monthid);
			
			if(null!=listPayments && !listPayments.isEmpty() && listPayments.get(0).getPaymentStatus().equalsIgnoreCase(EntertainmentConstant.PAYMENT_STATUS_PAID)) {
				listPayments.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPayments;
	}
	
	
	@Override
	public void insertPayment(Long memberId) {
		
		try {
			List<MonthMaster> listMonth=monthMasterRepository.findAll();
			
			if(null!=listMonth && ! listMonth.isEmpty()) {
				
				for (MonthMaster monthMaster : listMonth) {
					
					Payments payments=new Payments();
					payments.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_DUE);
					payments.setMonthId(monthMaster.getMonthId());
					payments.setAmount(new BigDecimal("0.0"));
					payments.setMemberId(memberId);
					payments.setMonth(monthMaster.getMonth());
					payments.setMonthId(monthMaster.getMonthId());
					payments.setYear(EntertainmentConstant.CURRENT_YEAR);

					payments.setPaymentId(sequenceDao.getNextSequenceId("payments"));

					paymentRepository.save(payments);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<MonthMaster> getMonthMaster() {
		
		List<MonthMaster> listPayments=null;
		try {
			listPayments=monthMasterRepository.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPayments;
	}
	
	
	@Override
	public void updatePayment(Payments payments,LoginInfo info) {
		
		try {
			
			List<Payments> list=paymentRepository.getPaymentsByMemberandMonth(payments.getMemberId(), payments.getMonthId());
			if(null !=list && !list.isEmpty()) {
				Payments existingPaymen=list.get(0);

				existingPaymen.setAmount(payments.getAmount());
				existingPaymen.setPaymentDate(new Date());
				existingPaymen.setPaymentStatus(EntertainmentConstant.PAYMENT_STATUS_PAID);

				paymentRepository.save(existingPaymen);
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void updateProfile(MemberDetails memberDetails,LoginInfo info) {
		
		try {

			MemberDetails existingMemberDetails =registrationRepository.findMemberByMemberId(memberDetails.getMemberId());


			existingMemberDetails.setMobileNumber(memberDetails.getMobileNumber());
			existingMemberDetails.setDeskPhoneNumber(memberDetails.getDeskPhoneNumber());
			existingMemberDetails.setDateOfBirth(memberDetails.getDateOfBirth());
			existingMemberDetails.setPassword(memberDetails.getPassword());

			registrationRepository.save(existingMemberDetails);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Notification> getNotification() {
		
		List<Notification> listNotification=null;
		try {
			listNotification=notificationRepository.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listNotification;
	}
	
	@Override
	public BigDecimal geCurrentFundBalance() {
		
		BigDecimal totalAmount=new BigDecimal("0.0");
		
		try {
			BigDecimal totalExpanditure=new BigDecimal("0.0");
			List<Expanditur> expanditurs=getExpanditure();
			
			if(null!=expanditurs && !expanditurs.isEmpty()) {
				for (Expanditur expanditur : expanditurs) {
					totalExpanditure=totalExpanditure.add(expanditur.getAmount());
				}
			}
			
			totalAmount=geTotalEarning().subtract(totalExpanditure);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalAmount;
	}

	
	
	@Override	
	public List<Expanditur> getExpanditure() {

		List<Expanditur> expanditurs=null;
		try {

			expanditurs=expanditurRepository.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expanditurs;
	}
	
	
	@Override
	public BigDecimal geTotalEarning() {
		
		BigDecimal totalEarn=new BigDecimal("0.0");
		try {
			List<Payments> list=paymentRepository.findAll();
			
			for (Payments payments : list) {
				totalEarn=totalEarn.add(payments.getAmount());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalEarn;
	}
	
	
	@Override
	public void insertExpanditur(Expanditur expanditur) {
		
		try {
			expanditur.setExpanditurId(sequenceDao.getNextSequenceId("expenditure"));
			expanditurRepository.save(expanditur);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
