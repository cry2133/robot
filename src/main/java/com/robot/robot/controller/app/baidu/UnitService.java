package com.robot.robot.controller.app.baidu;

import net.sf.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/*
 * unit对话服务
 */
public class UnitService {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    private static String utterance(String question) {
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
        try {
            // 请求参数
            String params = "{" +
                    "\"bot_session\":\"\"," +
                    "\"log_id\":\"201812130927\"," +
                    "\"request\":{" +
                        "\"bernard_level\":1," +
                        "\"client_session\":\"{" +
                            "\\\"client_results\\\":\\\"\\\"," +
                            "\\\"candidate_options\\\":[]}\"," +
                        //"\"query\":\"居民用户掌上营业厅办理过户\","+
                        "\"query\":\"" +question+ "\"," +
                        "\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"}," +
                        "\"updates\":\"\",\"user_id\":\"88888\"}," +
                    "\"bot_id\":\"20712\"," +
                    "\"version\":\"2.0\"}";
            String accessToken = "24.715078519b28cc8e1ee1572a55c2db9a.2592000.1547257352.282335-14394119";
            String result = HttpUtil.post(talkUrl, accessToken, "application/json", params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String unit(String question){
        String json = utterance(question);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject response = result.getJSONObject("response");
        List action_list = response.getJSONArray("action_list");
        Iterator it = action_list.iterator();
        String answer = "";
        while (it.hasNext()){
            JSONObject actoin = JSONObject.fromObject(it.next());
            if(actoin.getDouble("confidence") > 90){
                answer = actoin.getString("say").replace("<div>","\\r\\n");
                break;
            }
        }
        return answer.replace("</div>","");
    }


    public static void main(String[] args) {
        //AuthService.getAuth();
        String a = unit("居民");
        System.out.println(a);
    }
}

