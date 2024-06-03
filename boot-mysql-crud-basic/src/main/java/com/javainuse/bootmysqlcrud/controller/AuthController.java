package com.javainuse.bootmysqlcrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthController {	

	    @GetMapping
	    public String helloWorld() {
	        
	        return "helloworld";
	    }
	
}