package com.aexp.hackathon.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aexp.hackathon.dbms.AugMethods;
import com.aexp.hackathon.pojo.APIDetails;


@Controller
public class GetAllApiController {
    
    @RequestMapping(value="/getallapi", method=RequestMethod.GET)
    public @ResponseBody String provideInfo() {
    	
//    	//TODO
//    	
//    	List<APIDetails> apiDetails = new ArrayList<>();
//    	apiDetails.add(new APIDetails("jarname", "api", "download", "active"));
//    	apiDetails.add(new APIDetails("jarname2", "api2", "download2", "active"));
//    	apiDetails.add(new APIDetails("jarname3", "api3", "download3", "inactive"));
    	
    	JSONArray responseDB = new JSONArray();
		try {
			responseDB = AugMethods.getTableData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return responseDB.toString();
    }
    
}
