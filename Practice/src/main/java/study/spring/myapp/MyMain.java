package study.spring.myapp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyMain {

	public static void main(String[] args) {

		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
		IMyservice service = con.getBean("myService",IMyservice.class);
		System.out.println(service.tosay("tosay 메서드 start "));
		System.out.println(service.bye("bye 메서드 start"));
		
		IMyservice youService = con.getBean("youService : ",IMyservice.class);
		System.out.println(youService.tosay("my response : "));
		
		
		
		
		
		
	}

}
