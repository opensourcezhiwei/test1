package com.nnk.maven.test.boot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nnk")
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);

		Set<Object> set = new HashSet<Object>();
		app.setSources(set);
		app.run(args);

	}

}
