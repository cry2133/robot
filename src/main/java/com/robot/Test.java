package com.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.common.utils.TuLingUtils;
import com.robot.common.utils.XFyunUtils;

public class Test {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
//		System.out.println(
//				" _____   ____  ____   ____ _______  \n"+
//				"|  __ \\ / __ \\|  _ \\ / __ \\__   __| \n"+
//				"| |__) | |  | | |_) | |  | | | |    \n"+
//				"|  _  /| |  | |  _ <| |  | | | |    \n"+
//				"| | \\ \\| |__| | |_) | |__| | | |    \n"+
//				"|_|  \\_\\\\____/|____/ \\____/  |_| \n"
//				
//				);
//		
//		String s ="1";
//		if("1".equals(s)){
//			System.out.println(1);
//		}
//		if("1".equals(s)){
//			System.out.println(2);
//		}
//		if("1".equals(s)){
//			System.out.println(3);
//		}
		
		
		
		TuLingUtils tl = new TuLingUtils();
		XFyunUtils xf = new XFyunUtils();
		String question = "什么是人工智能";
		
		//tl.postTalk(question).getText();
		System.out.println(tl.postTalk(question).getText());
		//xf.runChat(question).getText();
		System.out.println(xf.runChat(question).getText());
	}
}
