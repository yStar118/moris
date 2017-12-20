package com.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * service层异常拦截记录
 * @author gaoyf
 *
 */

@Aspect
public class ServiceExceptionAspect {
	private static Logger logger = LoggerFactory.getLogger("serviceLog");

	@AfterThrowing(value = "execution (* com.*.service.*.*(..))", throwing = "e")
	public void loggingException(JoinPoint joinPoint, Exception e) {
		// 拦截的实体类
		Object target = joinPoint.getTarget(); // 拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		logger.error("实体类:" + target);
		logger.error("方法名:" + methodName);
		logger.error("异常类名：" + joinPoint.getSignature().getName().getClass());
		// 得到被拦截方法参数，并打印
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.error("抛异常拦截： 被拦截到的方法参数：" + i + " -- " + args[i]);
		}
		logger.error("异常信息: " + e.getMessage());
	}

	@AfterThrowing(value = "execution (* com.service.*.*(..))", throwing = "e")
	public void loggingException2(JoinPoint joinPoint, Exception e) {
		// 拦截的实体类
		Object target = joinPoint.getTarget(); // 拦截的方法名称
		String methodName = joinPoint.getSignature().getName();
		logger.error("实体类:" + target);
		logger.error("方法名:" + methodName);
		logger.error("异常类名：" + joinPoint.getSignature().getName().getClass());
		// 得到被拦截方法参数，并打印
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.error("抛异常拦截： 被拦截到的方法参数：" + i + " -- " + args[i]);
		}
		logger.error("异常信息: " + e.getMessage());
	}

	@Around("execution (* com.*.service.*.*(..))")
	public Object serviceExceptionIterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		try {
			logger.info("The method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName() + "() begins with "
					+ Arrays.toString(joinPoint.getArgs()));
			result = joinPoint.proceed();
			logger.info("The method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName() + "() ends with " + result);
		} catch (IllegalArgumentException iae) {// 捕获参数异常
			StringBuilder sb = new StringBuilder();
			sb.append(joinPoint.getTarget().getClass().getName() + " : " + Arrays.toString(joinPoint.getArgs()) + " in " + joinPoint.getSignature().getName()
					+ "()");
			iae.printStackTrace();
			logger.error(sb.toString());
			throw iae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return result;
	}

	@Around("execution (* com.service.*.*(..))")
	public Object serviceExceptionIterceptor2(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		try {
			logger.info("The method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName() + "() begins with "
					+ Arrays.toString(joinPoint.getArgs()));
			result = joinPoint.proceed();
			logger.info("The method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName() + "() ends with " + result);
		} catch (IllegalArgumentException iae) {// 捕获参数异常
			StringBuilder sb = new StringBuilder();
			sb.append(joinPoint.getTarget().getClass().getName() + " : " + Arrays.toString(joinPoint.getArgs()) + " in " + joinPoint.getSignature().getName()
					+ "()");
			iae.printStackTrace();
			logger.error(sb.toString());
			throw iae;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return result;
	}
}
