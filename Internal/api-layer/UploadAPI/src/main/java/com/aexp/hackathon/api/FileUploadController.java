package com.aexp.hackathon.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aexp.hackathon.JarExplorer;


@Controller
public class FileUploadController {
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name, 
            @RequestParam("file") MultipartFile file){
    	 String path = System.getProperty("user.dir");
    	 String BASE_PATH = path + "/uploadedJars/";
         // construct the complete absolute path of the file
    	 File inputFile = new File(BASE_PATH+name);
//    	 if( inputFile.exists() ){
//    		 return "You failed to upload " + name + "because a file with same name already exists";
//    	 }
        if (!file.isEmpty() ) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(inputFile));
                stream.write(bytes);
                stream.close();
                
                JarExplorer jr = new JarExplorer();
                jr.setJarFile(BASE_PATH+name);
//                jr.printMethodStubs();             
                return jr.printMethodStubs();                
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
