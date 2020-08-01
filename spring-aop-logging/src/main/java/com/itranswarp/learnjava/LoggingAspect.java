package com.itranswarp.learnjava;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	@Before("execution(public * com.itranswarp.learnjava.service.UserService.*(..))")
	public void doAccessCheck() {
		System.err.println("[Before] do access check...");
	}

	@Around("execution(public * com.itranswarp.learnjava.service.MailService.*(..))")
	public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
		// 内部决定是否执行方法，因此先打印日志，执行方法，日志。
		System.err.println("[Around] start " + pjp.getSignature());
		Object retVal = pjp.proceed();
		System.err.println("[Around] done " + pjp.getSignature());
		return retVal;
	}
}
