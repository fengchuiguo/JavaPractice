package com.fengchuiguo.Exception.demo;

import java.util.Scanner;

public class BookManagerEasy {
	private static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {
		//定义”图书“数组
		String[] books = { "C语言", "数据结构", "汇编语言", "高数", "大学语文", "毛概" };
		while (true) {
			System.out.println("输入命令：1-按照名称查找图书；2-按照序号查找图书");
			String book;
			try {
				//取得整型命令
				int command = inputCommand();
				//根据不同命令值，进行不同操作
				switch (command) {
				case 1://按照图书名称选择图书
					book = getBookByName(books);
					System.out.println("book:" + book);
					break;
				case 2://按照图书序号（数组下标）选择图书
					book = getBookByNumber(books);
					System.out.println("book:" + book);
					break;
				case -1://返回值为-1，说明输入有误
					System.out.println("命令输入错误！请根据提示输入数字命令！");
					continue;
				default://其他值的命令均认为是错误命令
					System.out.println("命令输入错误！");
					continue;
				}
				break;//退出程序
			} catch (Exception bne) {
				//捕获”图书不存在异常“时，要求重新输入命令
				System.out.println(bne.getMessage());
				continue;
			} 
		}
	}

	//按照图书名称查询图书
	private static String getBookByName(String[] books)
			throws Exception {
		System.out.println("输入图书名称：");
		//获取输入的图书名称
		String name = console.next();
		for (int i = 0; i < books.length; i++) {
			if (name.equals(books[i]))
				//输入的名称与某一图书名称匹配，返回该图书
				return books[i];
		}
		//若无匹配，抛出”图书不存在异常“
		throw new Exception("图书不存在！");
	}

	//根据图书序号（数组下标）查询图书
	private static String getBookByNumber(String[] books)
			throws Exception {
		while (true) {
			System.out.println("输入图书序号：");
			try {
				//获取输入的图书序号（数组下标）
				int index = inputCommand();
				//若返回值为-1
				if(index == -1){
					System.out.println("命令输入错误！请根据提示输入数字命令！");
					continue;
				}
				//若不出现”数组下标越界异常“，则返回相应位置的图书
				String book = books[index];
				return book;
			} catch (ArrayIndexOutOfBoundsException e) {
				//输入的序号不存在（引发”数组下标越界异常“），则抛出”图书不存在异常“
				Exception bookNotExists = new Exception("图书不存在！");
				bookNotExists.initCause(e);
				throw bookNotExists;
			}
		}
	}

	//从控制台输入命令，用于输入命令和输入图书序号
	private static int inputCommand(){
		int command;
		try {
			command = console.nextInt();
			return command;
		} catch (Exception e) {
			//若输入字符型或者字符串，则抛出异常，捕获该异常，抛出”错误命令异常“
			console = new Scanner(System.in);
			//返回-1
			return -1;
		}
	}
}
