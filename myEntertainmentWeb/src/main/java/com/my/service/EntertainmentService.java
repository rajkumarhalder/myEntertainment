package com.my.service;

import java.math.BigDecimal;
import java.util.List;

import com.my.dto.LoginInfo;
import com.my.dto.PaymentInfoDto;
import com.my.model.Expenditure;
import com.my.model.MemberDetails;
import com.my.model.MonthMaster;
import com.my.model.Notification;
import com.my.model.Payments;

public interface EntertainmentService {

	public Object memberRegistration(MemberDetails member, List<String> messageList, LoginInfo info);

	MemberDetails executeLogin(String userName, String passWord, List<String> errorMsgList);

	MemberDetails getMemberByMemberId(Long memberId, List<String> messageList);


	List<PaymentInfoDto> getPaymentInfo(Long memberId, List<String> messageList, int year, LoginInfo info);

	List<MonthMaster> getMonthMaster(LoginInfo info);


	void updateMember(MemberDetails memberDetails, List<String> messageList, LoginInfo info);

	List getDuePayments(Long memberId, int year, LoginInfo info);

	List<Notification> getNotification(LoginInfo info);

	BigDecimal geCurrentFundBalance(LoginInfo info);

	List<Expenditure> getExpanditure();

	BigDecimal geTotalEarning();

	void insertExpanditur(Expenditure expanditur);

	MemberDetails getMemberByUserCode(String userCode);

	void sendMail(String allUserMailFlag, Long targetUserId);

	public void saveOrUpdatePayment(Payments payments, List<String> errorMsgList, LoginInfo info);

	public List getAllMembers(List<String> messageList, LoginInfo info);

	public List<MemberDetails> getAllActiveMembers(List<String> messageList, LoginInfo info);


}
