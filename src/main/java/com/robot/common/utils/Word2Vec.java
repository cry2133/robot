/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2017-11-02 12:09</create-date>
 *
 * <copyright file="Demo.java" company="码农场">
 * Copyright (c) 2017, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.robot.common.utils;

import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 词向量的训练与应用
 *
 * @Author 	laoGF
 * @Version  1.0
 * @Data 2018/8/17
 */
public class Word2Vec {
    private static final String MODEL_FILE_NAME = "data/model/word_vector_model.txt";

    private static  WordVectorModel wordVectorModel = null;

    private  Word2Vec(){

    }

    /**
     * 获取<词>向量单例实例
     * @param model_file_name : 指定模型路径
     * @return
     * @throws IOException
     */
    public static Word2Vec getWordVectorModel(String model_file_name) throws IOException {
        if(StringUtils.isEmpty(model_file_name)){
            model_file_name = MODEL_FILE_NAME;
        }
        wordVectorModel = new WordVectorModel(model_file_name);
        return new Word2Vec();
    }



    /**
    * 查询与词语最相似的词语
    * @param word : 指定词语
    * @return 返回Map<词语，相似度>
    */
    public Map<String,Float> nearest(String word) throws IOException{
        Map<String,Float> map = new HashMap<>();
        for (Map.Entry<String, Float> entry : wordVectorModel.nearest(word)){
            map.put(entry.getKey(),entry.getValue());
        }
        return map;
    }



}
