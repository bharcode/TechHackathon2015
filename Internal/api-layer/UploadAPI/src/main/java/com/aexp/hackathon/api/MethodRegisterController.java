package com.aexp.hackathon.api;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aexp.hackathon.dbms.AugMethods;
import com.aexp.hackathon.document.ApifyService;
import com.aexp.hackathon.pojo.APIDetails;


@Controller
public class MethodRegisterController {

	URLClassLoader cl ;

//	@RequestMapping(value="/selectmethod", method=RequestMethod.GET)
//	public @ResponseBody String provideInfo() {
//		return "You can select the method to be APIfied a file by posting to this same URL.";
//	}

	private String outputJson = "";


	private String traverseFields (Class claszz, String outputJson) throws ClassNotFoundException {
		//StringBuilder outputJson = new StringBuilder("{");
		if(outputJson.equals("")){
			outputJson = "{";
		}
		Field[] fields = claszz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String fieldType = field.getGenericType().toString();
			outputJson = outputJson + "\"" + fieldName + "\":";
			//System.out.println(fieldName);
			//System.out.println(fieldType);
			if(!fieldType.equals("java.lang.String")){
				outputJson = outputJson + "{";
				Class claszzTemp = cl.loadClass(fieldType); 
				outputJson = traverseFields (claszzTemp, outputJson);
				outputJson = outputJson + "}";
			} else {
				outputJson = outputJson + "\"test\",";
			}
		}

		return outputJson;
	}


	@RequestMapping(value="/selectmethod", method=RequestMethod.POST)
	public @ResponseBody String handleMethodSelector(@RequestBody String input ) throws Exception{
		
		System.out.println("Called Select Method");
		System.out.println(input);

		JSONObject jobj = new JSONObject(input);
		String jarName = jobj.getString("jarname");
		String methodName = jobj.getString("method");
		String className = jobj.getString("classname");
		
		//System.out.println("&*&*&*"+cl);
		//System.out.println("()()()"+BASE_PATH+jarName);

		String path = System.getProperty("user.dir");

		String BASE_PATH =  path + "/uploadedJars/";
		cl  = URLClassLoader.newInstance(new URL[] {new File(BASE_PATH+jarName).toURI().toURL()});

		String[] firstBreak = methodName.split("\\(");
		String funcName = firstBreak[0].split(" ")[2];
		String outputType = firstBreak[0].split(" ")[1];
		String inputType = firstBreak[1].split("\\)")[0];

		long apiId = -1;
		try {
			apiId = AugMethods.updateTable(jarName, className, funcName, inputType, outputType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String downloadLink;
		String fileName = ""; 
		if(apiId != -1){
			fileName = funcName+"-"+apiId + ".doc";
			downloadLink = "http://"+Application.Hostname+":8080/downloadfile/"+funcName +"-"+apiId;
			AugMethods.updateDownloadLink(apiId, downloadLink);
		}

		String apiURL = "http://"+Application.Hostname+":8080/apify/v1/endpoint/"+funcName+"-"+apiId;
		Class inoutClass = cl.loadClass(inputType);
		String sampleRequestPayload = "";
		sampleRequestPayload = traverseFields (inoutClass, sampleRequestPayload);
		sampleRequestPayload = sampleRequestPayload + "}";
		sampleRequestPayload = sampleRequestPayload.replaceAll(",}", "}");
		//		System.out.println("#######"+sampleRequestPayload);

		String sampleResponsePaylaod = "";
		Class outputClass = cl.loadClass(outputType);
		sampleResponsePaylaod = traverseFields (outputClass, sampleResponsePaylaod);
		sampleResponsePaylaod = sampleResponsePaylaod + "}";
		sampleResponsePaylaod = sampleResponsePaylaod.replaceAll(",}", "}");
		
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode sampleResponseObject = mapper.readTree(sampleResponsePaylaod);
		sampleResponsePaylaod = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sampleResponseObject);
		//		System.out.println("#$#$#$$$######"+sampleResponsePaylaod);

		// Call Dhwanit

		ApifyService as = new ApifyService();
		as.makeApifyDocument(path + "/Documentation/"+fileName, apiURL, funcName+"-"+apiId, "POST", sampleRequestPayload, sampleResponsePaylaod, "application/json");

		JSONObject responseDB = new JSONObject();
		try {
			responseDB = AugMethods.getTableData(String.valueOf(apiId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("Access-Control-Allow-Origin", "*");
//		httpHeaders.set("Access-Control-Allow-Headers", "*");
//		
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseDB.toString(), httpHeaders, HttpStatus.OK);

		return responseDB.toString();
	}

}
