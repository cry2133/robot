package com.robot.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.corpus.synonym.Synonym;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.mining.word.NewWordDiscover;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * 
 * HanLP 常用接口工具类
 *
 * @Author 	laoGF
 * @Data 	2018年8月14日
 */
public class HanLPUtils {

	
	/**
	 * NLP分词提取关键词
	 * NLP分词 NLPTokenizer 会执行全部命名实体识别和词性标注。
	 * 所以速度比标准分词慢，并且有误识别的情况。
	 * @param question : 指定文本
	 * @return 返回分词后的set集合
	 */
	public static Set<String> getKeywordByNLPsegment(String question){
		Set<String> set = new HashSet<String>();
		List<Term> segToken = NLPTokenizer.segment(question);
		for(Term s : segToken){
			set.add(s.word);
		}
		return set;
	}
	
	
	/**
	 * 标准分词
	 * @param question : 指定文本
	 * @return 返回分词后的set集合
	 */
	public static Set<String> getKeywordByHanLP(String question){
		Set<String> set = new HashSet<String>();
		List<Term> keywordList = StandardTokenizer.segment(question.toCharArray());
		for(Term kw : keywordList){
			set.add(kw.word);
		}
		return set;
	}
	
	
	/**
	 * 关键词提取
	 * @param question : 指定文本
	 * @param size : 指定需要提取几个关键词
	 * @return 返回关键词集合
	 */
	public static List<String> getKeyword(String question,int size){
		return TextRankKeyword.getKeywordList(question,size);
	}


	/**
	 * 关键词提取  --根据文本长度提取
	 * @param question : 指定文本
	 * @return 返回关键词集合
	 */
	public static List<String> getKeyword(String question){
		int length = question.length();
		length = (length - 1) / 2 ;
		return TextRankKeyword.getKeywordList(question,length);
	}
	
	
	/**
	 * 智能推荐--搜索引擎
	 * @param list : 被搜索的内容集合
	 * @param question : 指定搜索的文本
	 * @param size : 指定搜索出来的数量，默认为 1
	 * @return 返回搜索集（已按权重排序）
	 */
	public static List<String> intelligentSearch(List<String> list,String question,int size){
		Suggester suggester = new Suggester();
		for (String title : list){
		    suggester.addSentence(title);
		}
		size = size == 0 ? 1 : size;
		return suggester.suggest(question,size);
	}


	/**
	 * 获取一个词的同义词
	 * @param word 词语
	 * @return 返回同义词列表
	 */
	public static Set<String> getSynonymItem(String word){
		Set<String> set = new HashSet<>();
		List<Synonym> synonymList = CoreSynonymDictionary.get(word).synonymList;
		for(Synonym s : synonymList){
			set.add(s.realWord);
		}
		return set;
	}



	/**
	 *
	 * 语义距离计算
	 * @param a 词1
	 * @param b 词2
	 * @return 返回两个词汇之间y的距离，意义越相近，距离越小
	 */
	public static Long semanticDistance(String a,String b){
		return CoreSynonymDictionary.distance(a, b);
	}


	/**
	 * 计算两个单词之间的相似度
	 * @param a 词1
	 * @param b 词2
	 * @return 返回0表示不相似，1表示完全相似
	 */
	public static double similarity(String a,String b){
		return CoreSynonymDictionary.similarity(a,b);
	}
	
	
    /**
     * 提取词语（新词发现）
     * @param text         大文本
     * @param size         需要提取词语的数量
     * @param newWordsOnly 是否只提取词典中没有的词语
     * @return 返回一个词语列表
     */
    public static List<WordInfo> extractWords(String text,int size,boolean newWordsOnly){
        NewWordDiscover discover = new NewWordDiscover(4, 0.0f, .5f, 100f, newWordsOnly);
        return discover.discover(text, size);
    }
	
    /**
     * 提取词语（新词发现）
     * @param reader       从reader获取文本
     * @param size         需要提取词语的数量
     * @param newWordsOnly 是否只提取词典中没有的词语
     * @return 返回一个词语列表
     */
    public static List<WordInfo> extractWords(BufferedReader reader,int size,boolean newWordsOnly) throws IOException{
        NewWordDiscover discover = new NewWordDiscover(4, 0.0f, .5f, 100f, newWordsOnly);
        return discover.discover(reader, size);
    }
	
}
