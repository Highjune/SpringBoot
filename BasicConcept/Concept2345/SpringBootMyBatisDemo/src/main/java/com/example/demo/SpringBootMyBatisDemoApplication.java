package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example") //메인이 controller들을 찾기 위해서. com.example.*의 모든 controller들을 다 찾는다.
public class SpringBootMyBatisDemoApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootMyBatisDemoApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMyBatisDemoApplication.class, args);
	}

}
