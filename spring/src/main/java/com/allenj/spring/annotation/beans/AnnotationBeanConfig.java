package com.allenj.spring.annotation.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AnnotationBeanConfig.class})
public class AnnotationBeanConfig {

}
