package com.jd.basicauth.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
	    origins = {"http://192.168.1.176:8080"},
	    allowCredentials = "true",
	    		methods = {RequestMethod.GET, RequestMethod.POST}
	)
@RequestMapping("public")
public class PublicController {	
	  @GetMapping
	    public ResponseEntity<Map<String, String>> getTest() {
	        // Implement your authentication logic here
	        // ...

	        // Return a JSON response
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "get auth successful");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }

	    @PostMapping
	    public ResponseEntity<Map<String, String>> postTest() {
	        // Implement your authentication logic here
	        // ...

	        // Return a JSON response
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "post auth successful");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
}