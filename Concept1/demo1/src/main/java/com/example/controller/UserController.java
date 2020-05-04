package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vo.UserVO;

@RestController
public class UserController {
	//localhost:8080/getUser
	
	@GetMapping("/getUser") 
	public UserVO getUser() {
		UserVO user = new UserVO();
		user.setUserid("jimin");
		user.setName("한지민");
		user.setGender("여");
		user.setCity("서울");
		return user;
	}
} 


