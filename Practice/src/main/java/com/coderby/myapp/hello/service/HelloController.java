package com.coderby.myapp.hello.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class HelloController {

	@Autowired
	@Qualifier("helloService")
	IHelloService helloService;
	//타입을 기준으로 찾아옴. @Autowired랑 @Qualifier 같이써야함
	
//	@Autowired
//	SellService sellService;
	
//	public HelloController(IHelloService helloService) {
//		this.helloService = helloService;
//	}
//	
	public void hello(String name) {
		System.out.println("HelloController : "+helloService.sayHello(name));
	}
	
	public void hello2(String name) {
		System.out.println("HelloController : "+helloService.sayHello2(name));
	}
	
	public void hello3(String name) {
		System.out.println("HelloController : "+helloService.sayHello3(name));
	}
	
	public void hello4(String name) {
		System.out.println("HelloController : "+helloService.sayHello4(name));
	}
	
	public void hello5(String name) {
		System.out.println("HelloController : "+helloService.sayHello5(name));
	}
}
