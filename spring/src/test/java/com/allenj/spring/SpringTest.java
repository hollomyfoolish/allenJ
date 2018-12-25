package com.allenj.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allenj.spring.service.BeanConfig;
import com.allenj.spring.service.EchoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class})
public class SpringTest {

	@Autowired
	private EchoService echoService;
	
	@Test
	public void testBeanNotNull() {
		Assert.assertNotNull(echoService);
	}
	
	@Test
	public void testAop() {
		echoService.doService("hello world");
	}
}
