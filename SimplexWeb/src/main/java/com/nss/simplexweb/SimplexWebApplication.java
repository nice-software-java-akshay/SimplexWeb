package com.nss.simplexweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.nss.simplexrest", "com.nss.simplexweb"})
public class SimplexWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplexWebApplication.class, args);
	}
}
