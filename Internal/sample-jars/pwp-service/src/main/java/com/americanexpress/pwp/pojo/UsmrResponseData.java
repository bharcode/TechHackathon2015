package com.americanexpress.pwp.pojo;

public class UsmrResponseData {
	
	private String cardNumber;
	
	private String mrPointBalance;
	
	private String mrPointBalanceCurrency;
	
	private String currency;
	
	private String timestamp;
	
	private String status;
	
	private String conversionRate;
	
	public String getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}

	public String getMrPointBalance() {
		return mrPointBalance;
	}

	public void setMrPointBalance(String mrPointBalance) {
		this.mrPointBalance = mrPointBalance;
	}

	public String getMrPointBalanceCurrency() {
		return mrPointBalanceCurrency;
	}

	public void setMrPointBalanceCurrency(String mrPointBalanceCurrency) {
		this.mrPointBalanceCurrency = mrPointBalanceCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
