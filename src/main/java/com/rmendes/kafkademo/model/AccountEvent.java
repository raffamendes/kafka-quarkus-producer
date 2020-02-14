package com.rmendes.kafkademo.model;

import java.math.BigDecimal;

public class AccountEvent {
	
	private String type;
	
	private BigDecimal value;
	
	private Long accountNumber;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	

}
