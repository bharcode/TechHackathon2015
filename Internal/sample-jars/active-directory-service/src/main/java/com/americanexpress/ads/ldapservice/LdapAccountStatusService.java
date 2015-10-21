package com.americanexpress.ads.ldapservice;

import com.americanexpress.ads.model.InputData;
import com.americanexpress.ads.model.LdapRequestObject;
import com.americanexpress.ads.model.LdapResponse;
import com.americanexpress.ads.model.ResponseData;

public class LdapAccountStatusService {
	
	public LdapResponse validateADSAccount (LdapRequestObject request) {
		
		LdapResponse ldapResponse = new LdapResponse();
		ResponseData responseData = new ResponseData();
		ldapResponse.setResponseData(responseData);
		InputData inputData = request.getInputData();
		
		responseData.setAdsAccountId(inputData.getAdsAccountId());
		
		responseData.setAccountSince("02 Oct 2011");
		responseData.setAccountRole("Admin, User");
		responseData.setAccountStatus("Active");
		
		if("akuma400".equals(inputData.getAdsAccountId().trim())){
			responseData.setAccountSince("02 July 2012");
			responseData.setAccountRole("User");
			responseData.setAccountStatus("Revoked");
		} else if("bchalv".equals(inputData.getAdsAccountId().trim())){
			responseData.setAccountSince("02 Nov 2013");
			responseData.setAccountRole("User");
			responseData.setAccountStatus("Expired");
		}
		
		return ldapResponse;
		
	}

}
