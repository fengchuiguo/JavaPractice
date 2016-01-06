package com.fengchuiguo.Exception.demo;

/**
 * 自定义异常类
 */
public class DrunkException extends Exception {

	public DrunkException(){
		
	}
	
	public DrunkException(String message){
		super(message);
		System.out.println("警察检查酒驾了："+message);
	}
}
