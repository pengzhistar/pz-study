package pz.study.annotation;
/**
 * RpcInt.java	  V1.0   2014年11月1日 上午11:41:01
 *
 * Copyright pengzhistar@sina.com All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//说明该注解将被包含在javadoc中
@Documented
// 字节码文件中存在并运行时可以获取
@Retention(RetentionPolicy.RUNTIME)
// 作用在接口、类、枚举、注解
@Target(ElementType.TYPE)
// 子类可以继承该注解
@Inherited
public @interface RpcInterface {
	public String name() default "";

}
