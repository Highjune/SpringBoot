package com.example.demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	//@RequestMapping(value="/hello", method=RequestMethod.GET)
	@GetMapping("/hello")
	public String hello(String name) {
		return "강준! : " + name;
	}
	
}
 