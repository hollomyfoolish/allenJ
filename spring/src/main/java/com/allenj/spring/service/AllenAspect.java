package com.allenj.spring.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allenj.spring.service.aop.HelpService;

@Aspect
@Component
public class AllenAspect {
	@Autowired
	private HelpService helpService;
	
	@Pointcut("execution(** com.allenj.spring.service.EchoServiceImpl.doService(..))")
	public void forDoServer() {}
	
	@Before("forDoServer()")
	public void standBy() {
		helpService.prepare();
	}
	
	@After("forDoServer()")
	public void congraduation() {
		helpService.congratuation();
	}
}
