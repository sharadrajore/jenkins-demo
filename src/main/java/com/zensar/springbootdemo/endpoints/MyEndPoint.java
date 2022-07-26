package com.zensar.springbootdemo.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "myEndPoint")
@Component
public class MyEndPoint {
	// http://localhost:8080/myactuator/myEndPoint  GET
	
	
	@ReadOperation
	public String sayHello() {
		return "<h2>Hello</h2>";
	}
	
	
	

}
