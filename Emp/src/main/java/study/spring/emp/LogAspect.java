package study.spring.emp;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	@Pointcut("execution(* study.spring.emp.hr.dao.EmpService.*(..))")
	private void empServicePointCut() {}	//포인트컷 이름 지정
	
	@Around("empServicePointCut()") //Around가 적용될 포인트컷이름 넣어주기
	public Object Log(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature s = joinPoint.getSignature();
		String methodName = s.getDeclaringTypeName();
		System.out.println("[Log] Before : "+methodName+" method time check Start!");

		long startTime = System.nanoTime();
		//nanoTime()을 쓰는 이유 ? 기준시간부터 절대값을 가져옴. 시간을 잴때 유용함 
		//currentTime(): 서버에서 여러개프로그램있을떄 (분산된 환경에서 시간을 잴떄 ) 각각의 기준시간이 다르기때문에 nanoTime()하기 어려움.
		Object result = null;
		result = joinPoint.proceed();

		long endTime = System.nanoTime();
		System.out.println("[Log] After : "+methodName+" method time check End!");
		System.out.println("[Log] "+methodName+" Processing time is "+(endTime-startTime)+"ns");
		return result;
	} 
	
}
