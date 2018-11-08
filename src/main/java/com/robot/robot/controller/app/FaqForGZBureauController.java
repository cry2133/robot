package com.robot.robot.controller.app;

import com.robot.common.utils.Base64Utils;
import com.robot.common.utils.RequestUtil;
import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 广州局问答特定接口
 *
 * @Author laoGF
 * @Version 1.0
 * @Data 2018年10月17日
 */
@Controller
@RequestMapping("/app/faq")
public class FaqForGZBureauController {

    public static Logger log = Logger.getLogger(FaqForAppController.class);

    //nobMh9Kdwa6YPYfdrSnE/swy93a3ihNUR0GQsp/L+w4
    private static final String PUB_KEY = "Tf4fJJZa2HBsgLqqhv3PQgLfhYyZkMd1tWv2qMSZCH8";
    //57640c3c24dbae4c52bbf66acb50616d
    private static final String PRIVATE_KEY = "8ba76237be53a9c8f49cb1f73c2278e0";
    private static final String URL = "http://211.159.158.75:8000/common/query";
    private static final String SESSION_ID = "e4e4b9dd-32f9-43bc-a54c-1651f88bd166";
    private static final String ACCOUNT = "13720185830";


    private FaqRequestBean faqBean = null;


    /**
     * 智能查询问答
     *
     * @param request 问题+机器人编号
     */
    @RequestMapping("/getAnswerByGZBureau")
    @ResponseBody
    public ResponseBean getAnswerByGZBureau(HttpServletRequest request) {

        String content = RequestUtil.getString(request, "content");

        faqBean = new FaqRequestBean();

        faqBean.setQuestion(content);

        String pubkeyEncode = URLEncoder.encode(PUB_KEY);
        String questionEncode = URLEncoder.encode(content);

        String rawStr = ACCOUNT + PUB_KEY + content + SESSION_ID + PRIVATE_KEY;
        Base64Utils encoder = new Base64Utils();
        String base64Str = encoder.encodeBase64(rawStr);
        base64Str = base64Str.replace("\n", "");
        String md5Str = MD5(base64Str);
        String param = "pubkey=" + pubkeyEncode +
                "&question=" + questionEncode +
                "&sign=" + md5Str +
                "&account=" + ACCOUNT +
                "&sessionId=" + SESSION_ID;
        log.info("广州局知识库返回内容：" + sendGet(URL,param));

        String ret = sendGet(URL,param);
        JSONObject json = JSONObject.fromObject(ret);
        String info = json.getString("info");
        String answer_type = json.getString("answer_type");

        String answer = null;

        String msg = json.getString("msg");
        if(!"success".equals(msg)){
            answer = "机器人大脑空白";
            log.info("-----知识库接口服务异常！-----" + msg);
        }

        int type = json.getInt("type");
        switch (type){
            case 100:
                log.info("------no answer------");
                break;
            case 200:
                answer = getAnswerByAnswerType(info,answer_type);
                log.info("------one answer------");
                break;
            case 300:
                answer = getAnswerByAnswerType(info,answer_type);
                log.info("------more answer------");
                break;
            default:
                log.info("------default------");
                break;
        }

        if(answer == null){
            answer = "机器人大脑空白";
        }

        faqBean.setAnswer(answer);
        //log.info(JsonFormatTool.formatJson(ret));

        return ResponseBean.success(faqBean);
    }

    /**
     * 根据问题类型解析答案
     *
     * @param info
     * @param answer_type
     * @return
     */
    private String getAnswerByAnswerType(String info, String answer_type){
        String answer = null;
        switch (answer_type){
            case "faq":
                answer = getAnswer(info);
                break;
            case "chat":
                answer = getAnswer2(info);
                break;
            case "kb":
                answer = getAnswer2(info);
                break;
            case "task":
                answer = getAnswer2(info);
                break;
            default:
                log.info("----返回的是未知答案类型----");
                break;
        }
        return answer;
    }



    /**
     * 解析业务型答案
     *
     * @param info
     * @return
     */
    private String getAnswer(String info){
        String answer = null;
        JSONArray jsonInfo = JSONArray.fromObject(info);

        double confidence = 0;
        String relate = null;

        List<String> list = new ArrayList<>();
        String returnQuestion = null;

        for(Object js : jsonInfo){
            JSONObject jsonObject = JSONObject.fromObject(js);
            String question = jsonObject.getString("question");
            list.add(question);

            if(confidence > 0){
                double confidence2 = jsonObject.getDouble("confidence");
                if(confidence < confidence2){
                    confidence = confidence2;
                    answer = jsonObject.getString("answer");
                    relate = str(jsonObject.getString("relate"));
                    returnQuestion = question;
                }
            }else{
                confidence = jsonObject.getDouble("confidence");
                answer = jsonObject.getString("answer");
                relate = str(jsonObject.getString("relate"));
                returnQuestion = question;
            }
        }

        list.remove(returnQuestion);
        faqBean.setQuestionList(list);

        if(relate == null){
            return answer;
        }
        return answer + relate;
    }


    /**
     * 解析闲聊型答案
     *
     * @param info
     * @return
     */
    private String getAnswer2(String info){
        JSONArray jsonInfo = JSONArray.fromObject(info);
        for(Object js : jsonInfo){
            JSONObject jsonObject = JSONObject.fromObject(js);
            return jsonObject.getString("answer");
        }
        return null;
    }


    /**
     * 答案与关联问题拼接
     *
     * @param relate
     * @return
     */
    private String str(String relate){
        String qtStr = null;
        JSONArray jsonRelate = JSONArray.fromObject(relate);
        for(int i=0; i<jsonRelate.size(); i++){
            JSONObject qtList = JSONObject.fromObject(jsonRelate.get(i));
            if(qtList.size() > 0){
                String qt = qtList.getString("qt");
                if(qtStr == null){
                    qtStr = "\n[" + (i+1) + "] " + qt;
                }else {
                    qtStr += "\n[" + (i+1) + "] " + qt;
                }
            }
        }
        return qtStr;
    }


    /**
     * 请求接口
     *
     * @param url
     * @param param
     * @return
     */
    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            //log.info(connection.getHeaderFields());
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch(Exception e){
            log.debug(e);
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                log.debug(e2);
            }
        }
        return result;
    }


    /**
     * 加密
     *
     * @param sourceStr
     * @return
     */
    private static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug(e);
        }
        return result;
    }



}
