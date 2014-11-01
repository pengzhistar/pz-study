/**
 * Main.java	  V1.0   2014年11月1日 下午3:16:56
 *
 * Copyright pengzhistar@sina.com All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package pz.study.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		//------------------解释spring mvc form表单处理 主动获取到bean 和方法，invoke----------------------
		Service s = new Service();
		Annotation[] anns = s.getClass().getAnnotations();
		for (Annotation annotation : anns) {
			if (RpcInterface.class.isInstance(annotation)) {
				System.out.println("注解类型:annotation");
				RpcInterface ann = (RpcInterface) annotation;
				System.out.println(ann.name());
			}
		}
		RpcInterface annsOfRpcInterface = s.getClass().getAnnotation(
				RpcInterface.class);
		System.out.println(annsOfRpcInterface.name());
		
		try {
			Method method = s.getClass().getMethod("say", new Class[]{String.class});
			RpcMethod rpcMethod = method.getAnnotation(RpcMethod.class);
			AuthLever level = rpcMethod.value();
			System.out.println(level);
			
			Annotation[][] paramAnns = method.getParameterAnnotations();
			for (Annotation[] param : paramAnns) {
				RpcParameter paramAnn = (RpcParameter)param[0];
				System.out.print(paramAnn.value()+" say:");
			}
			
			method.invoke(s, "hello world");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//-----------------------------解释mybatis SQL接口处理-------------------------------
		System.out.println("---------------------");
		Object proxy = Proxy.newProxyInstance(Main.class.getClassLoader(),
				new Class[] { IService.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						
						RpcMethod rpcMethod = method.getAnnotation(RpcMethod.class);
						AuthLever level = rpcMethod.value();
						System.out.println(level);
						
						Annotation[][] paramAnns = method.getParameterAnnotations();
						for (Annotation[] param : paramAnns) {
							RpcParameter paramAnn = (RpcParameter)param[0];
							System.out.print(paramAnn.value() + " say:");
						}
						System.out.println(args[0]);
						return null;
					}
				});
		IService proxyService = IService.class.cast(proxy);
		proxyService.say("hello world");
	}
}




