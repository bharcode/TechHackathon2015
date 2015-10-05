package com.aexp.hackathon.api;

import java.io.File;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static String Hostname;

    public static void main(String[] args) throws UnknownHostException {
//    	System.err.println(System.getProperty("user.name"));
    	java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
//    	System.out.println("Hostname of local machine: " + localMachine.getHostName());
    	
    	Hostname = localMachine.getHostName().toLowerCase();
    	
        SpringApplication.run(Application.class, args);
		String base_path = System.getProperty("user.dir");
		File file = new File(base_path+"/uploadedJars/");
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(base_path+"/Documentation/");
		if (!file.exists()) {
			file.mkdir();
		}
    }
}
