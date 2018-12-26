package com.allenj.spring.annotation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allenj.spring.annotation.beans.AnnotationBeanConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeanConfig.class})
public class AnnotationTest {
	@Autowired
	private ApplicationContext appCtx;
	
	@Test
	public void testGetBeanByAnnotaion(){
		String[] names = appCtx.getBeanNamesForAnnotation(Action.class);
		Assert.assertNotNull(names);
		Assert.assertEquals(2, names.length);
	}
}
