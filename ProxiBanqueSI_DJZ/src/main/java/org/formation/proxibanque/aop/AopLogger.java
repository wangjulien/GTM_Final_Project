package org.formation.proxibanque.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP logger, l'object est de logger tous les exceptions DaoException thrown
 * RESTE A FAIRE
 * 
 * @author JW
 *
 */
@Aspect
public class AopLogger {
	
	@Pointcut("execution(* *.*(..))")
	public void executeMethod() {
	}

	
	// @AfterThrowing("executeMethod()")
	// public void logDaoException( ) {
	// log
	// }

	@Around("executeMethod()")
	public void watchingPerformance(ProceedingJoinPoint jp) {
		try {
			// Do something before execution of the method
			
			jp.proceed();	// Execution of the method
			
			// Do something else after execution of the method
			
			
		} catch (Throwable e) {
			// Do something when these is an exception thrown
//			System.out.println(e.getMessage());
		}
	}
}
