package com.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
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
		
		/*
		
		TuLingUtils tl = new TuLingUtils();
		XFyunUtils xf = new XFyunUtils();
		String question = "什么是人工智能";
		
		//tl.postTalk(question).getText();
		System.out.println(tl.postTalk(question).getText());
		//xf.runChat(question).getText();
		System.out.println(xf.runChat(question).getText());
		*/
		
		/*
		JiebaSegmenter segmenter = new JiebaSegmenter();
	    String[] sentences =
	        new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
	                      "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
	    for (String sentence : sentences) {
	        //System.out.println(segmenter.process(sentence, SegMode.INDEX).toString());
	    }
	    String s = "《供电服务监管办法》规定向用户提供供电方案的期限为,自受理用户用电申请之日起,一般居民用户不超过5个工作日,低压电力用户不超过10个工作日,高压单电源用户不超过30个工作日,高压双电源用户不超过60个工";
	    //System.out.println(segmenter.process(s, SegMode.INDEX).toString());
		List<SegToken> segToken = segmenter.process(s, SegMode.INDEX);
		for (SegToken st : segToken ){
			System.out.println(st.word);
		}
	    */
	    
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 8);
		map.put("b", 12);
		map.put("c", 53);
		map.put("d", 33);
		map.put("f", 11);
		
		/*
		Collection<Integer> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		Object o =  obj[obj.length-1];
		System.out.println(o);
		*/
		
		//返回最大value值的key
	    List<Integer> list = new ArrayList<Integer>();
        for (String temp : map.keySet()) {
            int value = map.get(temp);
            list.add(value);
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int size = list.get(i);
            max = (max>size)?max:size;
        }
        for (String key : map.keySet()) {
            if (max == map.get(key)) {
                System.out.println( key + "=" + max);
            }
        }
		
	}
}
