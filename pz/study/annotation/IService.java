/**
 * IService.java	  V1.0   2014年11月1日 下午4:11:04
 *
 * Copyright pengzhistar@sina.com All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package pz.study.annotation;

@RpcInterface(name="MyService")
public interface IService {
	
	@RpcMethod(AuthLever.vip)
	public void say(@RpcParameter("pz") String say);
}

