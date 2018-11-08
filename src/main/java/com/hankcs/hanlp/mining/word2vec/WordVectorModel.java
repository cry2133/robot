/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-07-23 PM4:24</create-date>
 *
 * <copyright file="WordVectorMap.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.mining.word2vec;

import groovy.util.logging.Slf4j;

import java.io.IOException;
import java.util.*;


/**
 * 词向量模型
 *
 * @author hankcs
 */
@Slf4j
public class WordVectorModel extends AbstractVectorModel<String> {
    /**
     * 加载模型<br>
     *
     * @param modelFileName 模型路径
     * @throws IOException 加载错误
     */
    public WordVectorModel(String modelFileName) throws IOException {
        super(loadVectorMap(modelFileName));
    }

    /*获取缓存，如果不存在则重新加载*/
   // private static Map<String, String> getCacheMap ;

    private static TreeMap<String, Vector> vectorTreeMap = new TreeMap<>();


    public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WordVectorModel.class);

    /**
     * 加载模型
     *
     * @param modelFileName
     * @return 词向量 K-V
     * @throws IOException
     */
    private static TreeMap<String, Vector> loadVectorMap(String modelFileName) throws IOException {

        //TreeMap<String, String> setCacheMap = new TreeMap<String, String>();

        /*获取缓存，如果不存在则重新加载*/
        if (vectorTreeMap.size() <= 0) {

            VectorsReader reader = new VectorsReader(modelFileName);
            reader.readVectorFile();

            if (reader.vocab != null) {
                for (int i = 0; i < reader.vocab.length; i++) {
                    vectorTreeMap.put(reader.vocab[i],new Vector(reader.matrix[i]));
                    /*
                    String vocab = reader.vocab[i];
                    float[] vector = reader.matrix[i];
                    String s = "";
                    StringBuffer sb = new StringBuffer(s);
                    for (float v : vector) {
                        if (s == null || s == "") {
                            s = String.valueOf(v);
                            sb.append(s);
                        } else {
                            sb.append(",");
                            s = String.valueOf(v);
                            sb.append(s);
                        }
                    }
                    setCacheMap.put(vocab, sb.toString());
                    */
                }
            }
            /*if (setCacheMap.size() > 0) {
                getCacheMap = setCacheMap;
            }
            if (getCacheMap.size() > 0) {
                RedisUtil.setMap("robot_vector_map", getCacheMap);
            }*/

        }

        return vectorTreeMap;
    }

    /**
     * 返回跟 A - B + C 最相似的词语,比如 中国 - 北京 + 东京 = 日本。输入顺序按照 中国 北京 东京
     *
     * @param A 做加法的词语
     * @param B 做减法的词语
     * @param C 做加法的词语
     * @return 与(A - B + C)语义距离最近的词语及其相似度列表
     */
    public List<Map.Entry<String, Float>> analogy(String A, String B, String C) {
        return analogy(A, B, C, 10);
    }

    /**
     * 返回跟 A - B + C 最相似的词语,比如 中国 - 北京 + 东京 = 日本。输入顺序按照 中国 北京 东京
     *
     * @param A    做加法的词语
     * @param B    做减法的词语
     * @param C    做加法的词语
     * @param size topN个
     * @return 与(A - B + C)语义距离最近的词语及其相似度列表
     */
    public List<Map.Entry<String, Float>> analogy(String A, String B, String C, int size) {
        Vector a = storage.get(A);
        Vector b = storage.get(B);
        Vector c = storage.get(C);
        if (a == null || b == null || c == null) {
            return Collections.emptyList();
        }

        List<Map.Entry<String, Float>> resultList = nearest(a.minus(b).add(c), size + 3);
        ListIterator<Map.Entry<String, Float>> listIterator = resultList.listIterator();
        while (listIterator.hasNext()) {
            String key = listIterator.next().getKey();
            if (key.equals(A) || key.equals(B) || key.equals(C)) {
                listIterator.remove();
            }
        }

        if (resultList.size() > size) {
            resultList = resultList.subList(0, size);
        }

        return resultList;
    }

    @Override
    public Vector query(String query) {
        return vector(query);
    }
}