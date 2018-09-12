package com.example.authpoc.apigatewaytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayTestApplication.class, args);
	}
}
