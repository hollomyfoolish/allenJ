package com.allenJ.proxy;

import java.lang.reflect.Proxy;

public class AnnotationProxyTest {

	public static void main(String[] args) {
		Class<?>[] interfaces = new Class<?>[]{AnnotationInterface.class};
		AnnotationInterface proxy = (AnnotationInterface) Proxy.newProxyInstance(AnnotationProxyTest.class.getClassLoader(), interfaces, new ProxyInvokerHandler());
		System.out.println(proxy.doSomeThing());
	}

}
