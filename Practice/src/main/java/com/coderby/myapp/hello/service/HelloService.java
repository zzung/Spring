package com.coderby.myapp.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("HelloService.sayHello() 메서드 실행");
		String message = "Hello !"+name;
		return message;
	}

	@Override
	public String sayHello2(String name) {
		System.out.println("HelloService.sayHello()2 메서드 실행");
		String message = "Hello !"+name;
		return message;
	}

	@Override
	public String sayHello3(String name) {
		System.out.println("HelloService.sayHello()3 메서드 실행");
		String message = "Hello !"+name;
		return message;
	}

	@Override
	public String sayHello4(String name) {
		System.out.println("HelloService.sayHello()4 메서드 실행");
		String message = "Hello !"+name;
		return message;
	}

	@Override
	public String sayHello5(String name) {
		System.out.println("HelloService.sayHello()5 메서드 실행");
		String message = "Hello !"+name;
		return message;
	}

}
