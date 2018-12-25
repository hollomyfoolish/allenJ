package com.allenj.spring.service;

import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {

	@Override
	public String doService(String s) {
		System.out.println("performing ...");
		return "==EchoServiceImpl==:" + s;
	}

}
