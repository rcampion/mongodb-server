package com.rkc.zds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rkc.zds")
@SpringBootApplication
public class MongodbServerApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MongodbServerApp.class, args);
	}

}
