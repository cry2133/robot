package com.robot.common.utils;

import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 *
 * 词向量模型训练
 *
 * @Author 	laoGF
 * @Version  1.0
 * @Data 2018/8/17
 */
public class Word2VecTrain {
    private static final String TRAIN_FILE_NAME = "data/model/train_text.txt";
    private static final String MODEL_FILE_NAME = "data/model/word_vector_model.txt";


    /**
     * 执行训练 (默认训练文件)
     * @return 返回词向量模型
     */
    public static WordVectorModel trainOrLoadModel() throws IOException {
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        return trainerBuilder.train(TRAIN_FILE_NAME, MODEL_FILE_NAME);
    }


    /**
     * 执行训练
     * @param train_file_name : 输入语料文件
     * @param model_file_name : 输出模型路径
     * @return 返回词向量模型
     */
    public static WordVectorModel trainOrLoadModel(String train_file_name,String model_file_name) throws IOException {
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        return trainerBuilder.train(train_file_name, model_file_name);
    }

}
