package com.robot;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.hankcs.hanlp.dictionary.common.CommonSynonymDictionary;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ColtTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        colt();
    }

    public static void colt() {
        DoubleMatrix2D matrix;
        matrix = new DenseDoubleMatrix2D(3, 4);
        //matrix = new SparseDoubleMatrix2D(3,4); // 稀疏矩阵
        //matrix = new RCDoubleMatrix2D(3,4); // 稀疏行压缩矩阵
        System.out.println("初始矩阵");
        System.out.println(matrix);
        System.out.println("填充");
        matrix.assign(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        System.out.println(matrix);
        System.out.println("转置");
        DoubleMatrix2D transpose = Algebra.DEFAULT.transpose(matrix);
        System.out.println(transpose);
        System.out.println("矩阵乘法");
        System.out.println(Algebra.DEFAULT.mult(matrix, transpose));


        Map<String, Integer> map = new HashMap<>();
        map.put("a", 32);
        map.put("b", 3);
        map.put("d", 55);
        map.put("c", 3);
        map.put("tt", 1);

        System.out.println(integerMapSort(map));

    }

    public static Map<String, Integer> integerMapSort(Map<String, Integer> map) {
        Map<String, Integer> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }


}
