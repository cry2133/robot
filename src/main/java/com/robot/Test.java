package com.robot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.robot.common.utils.MapUtils;
import com.robot.common.utils.StringUtils;
import com.robot.common.utils.TuLingUtils;
import com.robot.common.utils.XFyunUtils;

public class Test {

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
		
		
		JiebaSegmenter segmenter = new JiebaSegmenter();
	    /*
		String[] sentences =
	        new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
	                      "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
	    for (String sentence : sentences) {
	        //System.out.println(segmenter.process(sentence, SegMode.INDEX).toString());
	    }
	    */

		
		String s = "电工进网";
	    //System.out.println(segmenter.process(s, SegMode.INDEX).toString());
		List<SegToken> segToken = segmenter.process(s, SegMode.INDEX);
		for (SegToken st : segToken ){
			System.out.println(st.word);
		}
		
	
		//System.out.println(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.valueOf("19931212"))));
		//System.out.println(Long.valueOf("1530677843978"));

		/*
        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "33");
        map.put("c", "44");
        map.put("d", "53");
        map.put("b", "1444");
        
        
        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //降序排序
            public int compare(Entry<String, String> o1,
                    Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
            
        });
        
        for(Map.Entry<String,String> mapping:list){ 
               System.out.println(mapping.getKey()+":"+mapping.getValue()); 
          } 
    */

		/*
        Map<String, Double> unsortMap = new HashMap<String, Double>();
        unsortMap.put("z", 10.7);
        unsortMap.put("b", 55.6);
        unsortMap.put("a", 6.0);
        unsortMap.put("c", 20.0);
        unsortMap.put("d", 1.6);
        unsortMap.put("e", 7.6);
        unsortMap.put("y", 86.6);
        unsortMap.put("n", 99.6);
        unsortMap.put("g", 50.6);
        unsortMap.put("m", 2.6);
        unsortMap.put("f", 9.6);
 
        System.out.println("Original...");
        System.out.println(unsortMap);
 
        //sort by values, and reserve it, 10,9,8,7,6...
        Map<String, Double> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
 
 
        //Alternative way
        Map<String, Double> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
 
        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result2);
		*/
        
		
		
		/*
		String content = "非居民";
		Pattern p=Pattern.compile(content);  
        Matcher m=p.matcher("请问非居民用户");    //正则表达式匹配
        boolean mm = m.find();
        System.out.println(mm);
        System.out.println(m.find());
        if(m.find()){
        	System.out.println("=====");
        }
        if(m.find()){
        	System.out.println("---------");
        }
        System.out.println(m.find());
        */
        
	    /*
		Map<String,Double> map4 = new HashMap<String,Double>();
		map4.put("a", 4.8);
		map4.put("b", 4.7);
		map4.put("c", 4.0);
		List<Double> list = new ArrayList<Double>();
	    String returnKey = "";
        for (String temp : map4.keySet()) {
            double value = map4.get(temp);
            list.add(value);
        }
        double max = 0;
        for (int i = 0; i < list.size(); i++) {
            double size = list.get(i);
            max = (max>size)?max:size;
        }
        for (String key : map4.keySet()) {
            if (max == map4.get(key)) {
            	returnKey =  key;
            }
        }		
		System.out.println(returnKey);
		*/
	}
}
