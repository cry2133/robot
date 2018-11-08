package com.robot.robot.common;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.utility.Predefine;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * 获取HanLP路径
 */
public class HanLPUrl {

    public static Logger log = Logger.getLogger(HanLPUrl.class);



    public static String getHanLPUrl(){
        // 自动读取配置
        Properties properties = new Properties();
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = HanLP.Config.class.getClassLoader();
            }
            try {
                properties.load(new InputStreamReader(Predefine.HANLP_PROPERTIES_PATH == null ?
                        loader.getResourceAsStream("hanlp.properties") :
                        new FileInputStream(Predefine.HANLP_PROPERTIES_PATH)
                        , "UTF-8"));
            } catch (Exception e) {
                String HANLP_ROOT = System.getenv("HANLP_ROOT");
                if (HANLP_ROOT != null) {
                    HANLP_ROOT = HANLP_ROOT.trim();
                    properties = new Properties();
                    properties.setProperty("root", HANLP_ROOT);
                    log.info("使用环境变量 HANLP_ROOT=" + HANLP_ROOT);
                } else{
                    log.debug(e);
                }
            }
            return properties.getProperty("root", "").replaceAll("\\\\", "/");
        }catch (Exception e){
            log.debug(e);
        }
        return null;
    }




}
