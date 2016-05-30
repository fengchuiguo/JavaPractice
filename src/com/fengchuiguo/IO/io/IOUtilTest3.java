package com.fengchuiguo.IO.io;

import java.io.File;
import java.io.IOException;

public class IOUtilTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IOUtil.copyFile(new File("e:\\javaio\\test.txt"), new File(
					"e:\\javaio\\test1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
