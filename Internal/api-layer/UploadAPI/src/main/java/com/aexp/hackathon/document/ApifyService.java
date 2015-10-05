package com.aexp.hackathon.document;

import java.io.File;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.json.JSONObject;

import com.aexp.hackathon.document.ApiSpec;
import com.aexp.hackathon.document.MethodArgument;
import com.aexp.hackathon.document.ApifyDocHelper;
/*
@Path("/apidoc")*/
public class ApifyService  {

	public void makeApifyDocument(String fileName, String apiUrl, String apiName, String methodType, String requestPayload, String responsePayload, String contentType) throws Exception {
		
//		JSONObject jobj = new JSONObject(requestPayload);
//		requestPayload = jobj.toString(4);
//		
//		jobj = new JSONObject(responsePayload);
//		responsePayload = jobj.toString(4);
		
		ApiSpec asp = new ApiSpec(apiUrl, apiName, methodType, requestPayload, responsePayload, contentType);
		
		//This will create a word document
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
		ApifyDocHelper.createStyledDocumentFromApiSpec(wordMLPackage, asp);
		
		// Now save it at the following location
		wordMLPackage.save(new java.io.File(fileName));
	}
}