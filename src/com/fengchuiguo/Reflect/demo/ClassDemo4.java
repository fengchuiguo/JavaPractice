package com.fengchuiguo.Reflect.demo;

public class ClassDemo4 {
	public static void main(String[] args) {
		ClassUtil.printFieldMessage("hello");
		System.out.println("=============");
		ClassUtil.printFieldMessage(new Integer(1));
	}
}
