package com.allenJ.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvokerHandler implements InvocationHandler{
	private InnerInstance innerInstance;

	public ProxyInvokerHandler(){
		innerInstance = new InnerInstance();
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		Method me = innerInstance.getClass().getMethod(method.getName(), method.getParameterTypes());
		return me.invoke(innerInstance, args);
	}

}
