/**
z * Main.java	  V1.0   2014年9月26日 下午10:01:39
 *
 * Copyright pengzhistar@sina.com All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package per.code.pz;

import java.util.HashMap;

public class Main extends Thread{
	
	public HashMap<String, ResultHandler> map = new HashMap<String, ResultHandler>();
	
	class Result{
		Object object;
	}
	
	interface ResultHandler {

		void getResult(Object obj);
	}
	
	private final Result result = new Result();
	
	@Override
	public void run() {
		//模拟服务端响应
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ResultHandler r = map.remove("test");
		if(r == null){
			System.out.println("响应超时");
			return;
		}
		r.getResult("ok");
	}
	
	public static void main(String[] args) {
		Main m =new Main();
		m.start();
		String str = (String) m.doGet();
		System.out.println(str);
	}
	public Object doGet(){
		synchronized(result){
			invoke("test", new ResultHandler() {
				public void getResult(Object message) {
					synchronized (result) {
						result.object = message;
						result.notify();
					}
				}
			});
			try {
				//模拟http超时
				result.wait(400);
				map.remove("test");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return result.object;
		}
	}
	
	void invoke(String str , ResultHandler headler ){
		map.put(str, headler);
	}
}
