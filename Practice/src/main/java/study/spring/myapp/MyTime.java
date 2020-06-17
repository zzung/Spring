package study.spring.myapp;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyTime {
	
	@Pointcut(value="execution(* study.spring.myapp..YouService.*(..))")
	private void youServicePointCut() {} //이게 포인트컷의 아이디가됨

	
	@Before("youServicePointCut()") //포인트컷 아이디 넣어주기
	public void nextTime() {
		System.out.println(new java.util.Date());
	}
	
	
	
}//항상 @Component + @Aspect로 공통코드 객체 지정하고,
//@PointCut 지정후, 아무기능 없는 메서드 하나 생성, (메서드이름=포인트컷 아이디)
//@Before/Around 등 aspect가 실행될 시기 정해주기, (매개변수로 포인트컷 아이디 넣어주기)
