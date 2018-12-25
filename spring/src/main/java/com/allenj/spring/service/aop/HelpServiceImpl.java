package com.allenj.spring.service.aop;

import org.springframework.stereotype.Component;

@Component
public class HelpServiceImpl implements HelpService {

	@Override
	public void prepare() {
		System.out.println("preparing ...");
	}

	@Override
	public void congratuation() {
		System.out.println("good show ...");
	}

}
