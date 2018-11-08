package com.robot.common.utils;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lenovo
 * @Create 2018-09-07 11:01
 * @Desc ${DESCRIPTION}
 */
public class Doc2Vec {
    private static final String MODEL_FILE_NAME = "data/model/word_vector_model.txt";

    private static DocVectorModel docVectorModel = null;

    private  Doc2Vec(){

    }

    public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Doc2Vec.class);

    /**
     *
     * 获取<文档>向量单例实例
     *
     * @param model_file_name : 指定模型路径
     * @return
     */
    public static Doc2Vec getDocVectorModel(String model_file_name) {
        if(StringUtils.isEmpty(model_file_name)){
            model_file_name = MODEL_FILE_NAME;
        }
        WordVectorModel wordVectorModel = null;
        try {
            wordVectorModel = new WordVectorModel(model_file_name);
        }catch (IOException IOE){
            log.debug(IOE);
        }
        docVectorModel = new DocVectorModel(wordVectorModel);
        return new Doc2Vec();
    }



    /**
     * 文档相似度比较
     * @param docA 词1
     * @param docB 词2
     * @return 返回文档相似度
     */
    public double docSimilarity(String docA,String docB){
        double rt = 0;
        try {
            rt = docVectorModel.similarity(docA,docB);
        }catch (Exception e){
            log.debug(e);
        }
        return rt;
    }


    /**
     * 查询最相似的前10个文档
     * @param document : 查询文档
     * @param documents : 指定文档集
     * @return 返回Map<文档，相似度>
     */
    public Map<String,Float> nearestDocument(String document, String[] documents) {
        for (int i = 0; i < documents.length; i++) {
            docVectorModel.addDocument(i, documents[i]);
        }
        Map<String,Float> map = new HashMap<>();
        try {
            for (Map.Entry<Integer, Float> entry : docVectorModel.nearest(document)) {
                map.put(documents[entry.getKey()],entry.getValue());
            }
        }catch (Exception IOE){
            log.debug(IOE);
        }
        return map;
    }



    /**
     * 查询最相似的前10个文档
     * @param document : 查询文档
     * @param documents : 指定文档集
     * @return 返回Map<文档，相似度>
     */
    public Map<String,Float> nearestDocument(String document, List<String> documents) {
        for (int i = 0; i < documents.size(); i++) {
            docVectorModel.addDocument(i, documents.get(i));
        }
        Map<String,Float> map = new HashMap<>();
        try {
            for (Map.Entry<Integer, Float> entry : docVectorModel.nearest(document)) {
                map.put(documents.get(entry.getKey()),entry.getValue());
            }
        }catch (Exception IOE){
            log.debug(IOE);
        }
        return map;
    }


}
