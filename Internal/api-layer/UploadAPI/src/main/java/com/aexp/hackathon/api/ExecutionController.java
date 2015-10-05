package com.aexp.hackathon.api;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aexp.hackathon.dbms.AugMethods;


@Controller
public class ExecutionController {
	@RequestMapping(value = "/apify/v1/endpoint/{apiname}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody
	public String callMethod(@PathVariable("apiname") String apiname, @RequestBody String input) throws Exception {
		
		System.out.println("%%%%%% "+ apiname);
		
		//TODO
		// 1. Check if API Name exist in DB
		String path = System.getProperty("user.dir");
		String BASE_PATH =  path + "/uploadedJars/";
		
		String apiId = apiname.split("-")[1];
		String method = apiname.split("-")[0];
		JSONObject jObj = AugMethods.getTableData(apiId, method);
		if(null == jObj || jObj.length() ==0){
			return "No Such Registered API, please check the apiname again";
		}
		String jarName = jObj.getString("jarname");
		String className = jObj.getString("classname");
		String methodName = jObj.getString("methodname");
		String parameter = jObj.getString("inputparams");
		
		
		
		//String[] inputCompo = input.split(",");
		
		URLClassLoader cl = URLClassLoader.newInstance(new URL[] {new File(BASE_PATH+jarName).toURI().toURL()});
		Class myClass = cl.loadClass(className);
		
		Class pojoClass = cl.loadClass(parameter);
		
		ObjectMapper om = new ObjectMapper();
		
		Object ob = om.readValue(input, pojoClass);
	
		//Class myClass =Class.forName("com.americanexpress.apicoe.Math"); 
		Method printMeMethod = myClass.getMethod(methodName, new Class[] {pojoClass});
		Object myClassObj = myClass.newInstance();
		Object response = printMeMethod.invoke(myClassObj, ob);
		return om.writeValueAsString(response);
	}

}
