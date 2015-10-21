package com.americanexpress.merchant.dempgraphic.service;

import com.americanexpress.merchant.dempgraphic.pojo.MerchantInformationRequestData;
import com.americanexpress.merchant.dempgraphic.pojo.MerchantInformationResponseData;
import com.americanexpress.merchant.dempgraphic.pojo.Request;
import com.americanexpress.merchant.dempgraphic.pojo.Response;

public class MerchantInformationService {
	
	public Response merchantInfoService (Request request) {
		
		MerchantInformationRequestData requestData = request.getMerchantInformationRequestData();
		
		Response response = new Response();
		
		MerchantInformationResponseData merchantInformationResponseData = new MerchantInformationResponseData();
		response.setMerchantInformationResponseData(merchantInformationResponseData);
		
		merchantInformationResponseData.setSe10(requestData.getSe10().trim());
		merchantInformationResponseData.setSeName("Dominos Pizza, Kormangala, Bangalore");
		merchantInformationResponseData.setSeCategory("Food and Restaurants");
		merchantInformationResponseData.setSeZipCode("560047");
		merchantInformationResponseData.setSeLatlong("12.9259,77.6229");
		
		if ("1000000001".equals(requestData.getSe10().trim())){
			merchantInformationResponseData.setSeName("Croma Electronics, Bangalore");
			merchantInformationResponseData.setSeCategory("Retail Store");
			merchantInformationResponseData.setSeZipCode("560021");
			merchantInformationResponseData.setSeLatlong("11.1126,77.2219");
		} else if ("1000000002".equals(requestData.getSe10().trim())){
			merchantInformationResponseData.setSeName("Orange Hotel, Outer Ringroad, Bangalore");
			merchantInformationResponseData.setSeCategory("Hotels");
			merchantInformationResponseData.setSeZipCode("560017");
			merchantInformationResponseData.setSeLatlong("12.1126,78.4523");
		}
		
		return response;
		
	}

}
