package com.shellofmagic.web;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShellofmagicApplication {
	
//	@PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//    }

	public static void main(String[] args) {
		SpringApplication.run(ShellofmagicApplication.class, args);
	}

}
