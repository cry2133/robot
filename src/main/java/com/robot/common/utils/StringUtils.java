package com.robot.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robot
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{


    /**
     *
     * @param target
     * @param isDeleteRepeat 是否去掉重复的字母例如abcda去重则为abcd不去重则为abcda即2个a当做不同的字母看
     * @return
     */
    public static List<String> split(String target, boolean isDeleteRepeat){
        List<String> list=new ArrayList<String>();
        for(int i=0;i<target.length();i++){
            if(!(isDeleteRepeat&&list.contains(String.valueOf(target.charAt(i))))){
                list.add(String.valueOf(target.charAt(i)));}
        }
        return list;
    }

    public static List<String> ger(List<String> string){
        List<String> list = new ArrayList<String>();
        for(int i=1;i<=string.size();i++){
            sort(string,new ArrayList<String>(),i);
        }
        return list;
    }
    private static void sort(List datas, List target,int num) {
        if (target.size() == num) {
            String string = "";
            for (Object obj : target){
                string += obj;
            }
            System.out.println(string);
            return ;
        }
        for (int i = 0; i < datas.size(); i++) {
            List newDatas = new ArrayList(datas);
            List newTarget = new ArrayList(target);
            newTarget.add(newDatas.get(i));
            newDatas.remove(i);
            sort(newDatas, newTarget,num);
        }
    }


}
