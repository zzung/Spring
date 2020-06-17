package study.spring.myapp;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


//공통코드를 정의한 클래스에 지정. 
//공통코드 객체가 빈으로 등록되어야하니까 @Component와 @Aspect 어노테이션 항상 같이 써주기
@Component
@Aspect  
public class TimeTracer {

	@Pointcut(value="execution(* study.spring.myapp..MyService.bye(..))")
	private void myServicePointCut() {} //이게 포인트컷의 아이디가됨
	

	@Around("myServicePointCut()") //포인트컷 아이디 넣어주기
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log]Before: "+methodName +" time check start");
		long startTime = System.nanoTime();
		Object result = null;
		try {
			result = joinPoint.proceed(); //실행하는 애
		}catch (Exception e){
			System.out.println("[Log]Exception: "+e.getMessage());
		}finally {
			System.out.println("[Log]Finally: "+methodName+" End.");
			
		}
		long endTime = System.nanoTime();
		System.out.println("[Log]After: "+methodName +" time check end");
		System.out.println("[Log]"+methodName +" Processing time is "+(endTime-startTime)+"ns");
		return result;
		
	}
	
	
}
