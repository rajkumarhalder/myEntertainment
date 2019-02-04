package com.my.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="expenditure")
public class Expenditure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5756919696314852534L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPENDITURE_SEQ")
    @SequenceGenerator(sequenceName = "entmt.expenditure_seq", allocationSize = 1, name = "EXPENDITURE_SEQ")
	private Long id;
	private Long monthId;
	private Long year;
	private BigDecimal amount;
	private Long expanditurDate;
	private String purpose;
	private String shortDesc;
	private Integer isActive;
	private Long createId;
	private Date createDate;
	private Long updateId;
	private Date updateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMonthId() {
		return monthId;
	}
	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getExpanditurDate() {
		return expanditurDate;
	}
	public void setExpanditurDate(Long expanditurDate) {
		this.expanditurDate = expanditurDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
