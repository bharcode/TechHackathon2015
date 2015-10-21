package com.americanexpress.pwp.service;

import java.util.Date;

import com.americanexpress.pwp.pojo.UsmrRequestData;
import com.americanexpress.pwp.pojo.UsmrRequestPojo;
import com.americanexpress.pwp.pojo.UsmrResponseData;
import com.americanexpress.pwp.pojo.UsmrResponsePojo;

public class UsmrService {
	
	public UsmrResponsePojo getPointBalance (UsmrRequestPojo usmrRequestPojo) {
		
		UsmrResponsePojo usmrResponsePojo = new UsmrResponsePojo();
		UsmrResponseData usmrResponseData = new UsmrResponseData();
		usmrResponsePojo.setUsmrResponseData(usmrResponseData);
		
		UsmrRequestData usmrRequestData = usmrRequestPojo.getUsmrRequestData();
		usmrResponseData.setCardNumber(usmrRequestData.getCardNumber());
		usmrResponseData.setStatus("Success");
		usmrResponseData.setCurrency("USD");
		usmrResponseData.setTimestamp(new Date().toString());
		usmrResponseData.setConversionRate("100");
		
		usmrResponseData.setMrPointBalance("4768");
		usmrResponseData.setMrPointBalanceCurrency("47.68");
		
		if("370000000000001".equals(usmrRequestData.getCardNumber().trim())){
			usmrResponseData.setMrPointBalance("254");
			usmrResponseData.setMrPointBalanceCurrency("2.54");
		} else if ("370000000000002".equals(usmrRequestData.getCardNumber().trim())){
			usmrResponseData.setMrPointBalance("345654");
			usmrResponseData.setMrPointBalanceCurrency("34.5654");
		} else if ("370000000000003".equals(usmrRequestData.getCardNumber().trim())){
			usmrResponseData.setMrPointBalance("7654");
			usmrResponseData.setMrPointBalanceCurrency("76.54");
		}
		
		return usmrResponsePojo;
		
	}

}
