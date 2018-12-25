package com.allenj.spring.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.allenj.spring.service.aop.AopConfig;

@Configuration
@ComponentScan(basePackageClasses = {BeanConfig.class, AopConfig.class})
@EnableAspectJAutoProxy
public class BeanConfig {

}
