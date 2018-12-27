package com.allenj.spring.annotation;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
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
		
		Map<String, Object> beans = appCtx.getBeansWithAnnotation(Action.class);
		for(Entry<String, Object> bean : beans.entrySet()){
			String beanName = bean.getKey();
			Action action = appCtx.findAnnotationOnBean(beanName, Action.class);
			System.out.println(beanName + ": " + action.name());
			Action annotationOnBean = AnnotatedElementUtils.findMergedAnnotation(bean.getValue().getClass(), Action.class);
			System.out.println(beanName + ": " + annotationOnBean.name());
		}
	}
}
