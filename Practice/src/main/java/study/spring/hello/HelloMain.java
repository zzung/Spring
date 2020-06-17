package study.spring.hello;

import java.util.Arrays;
import org.springframework.aop.interceptor.ConcurrencyThrottleInterceptor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.coderby.myapp.hello.service.HelloController;

public class HelloMain {

	public static void main(String[] arg) {
		AbstractApplicationContext con = new GenericXmlApplicationContext("application-config.xml");
//		System.out.println(Arrays.toString(con.getBeanDefinitionNames()));
		HelloController control = con.getBean("helloController", HelloController.class);
		control.hello("승희");
		control.hello2("승희");
		control.hello3("승희");
		control.hello4("승희");
//		HelloController control2 = con.getBean("helloController", HelloController.class);
//		System.out.println(control);
//		System.out.println(control2);
		control.hello("d");
		
		//bean을 뺄때 singleton / prototype 으로 했을때 비교하기
		//싱글톤은 객체를 하나만 빼오기 때문에 이걸로 쓰게되면 무조건 한개의 객체를 돌려쓰는것.
		// 프로토타입은 빈을 빼올때마다 객체를 빼오기 때문에 주소값이 다름.
		
		con.close();
	}
	
}
