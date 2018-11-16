package com.robot;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.seg.common.Term;
import com.robot.common.utils.*;
import com.robot.robot.controller.app.FaqForGZBureauController;
import com.robot.robot.domain.TFaqDO;
import org.springframework.beans.factory.annotation.Autowired;

import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.robot.robot.controller.app.bean.ResponseBean;

public class Test {


	public static void hehe()  {
		System.out.println(HanLPUtils.similarity("头痛","头疼"));
		String doc1 = "低压客户是什么？";
		String doc2 = "低压客户是指什么？";
		System.out.println(Doc2Vec.getDocVectorModel("C:/Users/lenovo/Desktop/HanLP/data/model/word_vector_model.txt").docSimilarity(doc1,doc2));
	}



	public static void main(String[] args) throws Exception {



		hehe();

		System.out.println(
				" _____   ____  ____   ____ _______  \n"+
				"|  __ \\ / __ \\|  _ \\ / __ \\__   __| \n"+
				"| |__) | |  | | |_) | |  | | | |    \n"+
				"|  _  /| |  | |  _ <| |  | | | |    \n"+
				"| | \\ \\| |__| | |_) | |__| | | |    \n"+
				"|_|  \\_\\\\____/|____/ \\____/  |_| \n"
				);

		/*
		//String s = "我觉得头晕，持续性，前额后枕部痛，睡眠好";
		//String s2 = "我觉得有点头晕，持续性的，前额后枕部有点痛，睡眠也很好好";
		String s = "我觉得有点头晕呀，持续性的,不是啊";
		String [] replace = {"的","啊","呀","哦"};

		for(String rp : replace){
			s = s.replace(rp,"");
			System.out.println(s);
		}
		//String s = "不是啊";
		String s2 = "不是的";
		System.out.println(HanLPUtils.getKeyword("是啊",1));
		//System.out.println(HanLPUtils.getKeywordByNLPsegment(s));
		//System.out.println(HanLPUtils.getKeywordByNLPsegment(s2));
		System.out.println(HanLPUtils.semanticDistance(s,s2));
		System.out.println(HanLPUtils.similarity(s,s2));
		//System.out.println(Doc2Vec.getDocVectorModel(null).docSimilarity(s,s2));
*/




		//List<String> list = HanLPUtils.getKeyword(s,10);
//		List<String> list = HanLP.extractKeyword(s,3);
		/*List<Term> cs= HanLP.segment(s);
		Iterator<Term> it=  cs.iterator();
		while (it.hasNext()){
			Term t=it.next();

			System.out.println(t.word);
			System.out.println(t.nature);
		}*/
//		for(String ss : list){
//			System.out.println(ss);
//		}



/*
		//创建文本语义理解对象
		TextUnderstander mTextUnderstander = new TextUnderstander( ); 
		//开始语义理解
		mTextUnderstander.understandText("今天的天气", searchListener);
		//初始化监听器
		TextUnderstanderListener searchListener = new TextUnderstanderListener(){
		    //语义结果回调
		    public void onResult(UnderstanderResult result){}
		    //语义错误回调
		    public void onError(SpeechError error) {}
		};
		*/
		
		
		
		/*
		
		TuLingUtils tl = new TuLingUtils();
		XFyunUtils xf = new XFyunUtils();
		String question = "什么是人工智能";
		
		//tl.postTalk(question).getText();
		System.out.println(tl.postTalk(question).getText());
		//xf.runChat(question).getText();
		System.out.println(xf.runChat(question).getText());
		*/




	
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
        Matcher m3=p.matcher("请问非居民用户44");    //正则表达式匹配
        if(m3.find()){
        	System.out.println("-----");
        }
        if(m.find()){
        	System.out.println("=====");
        }
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
