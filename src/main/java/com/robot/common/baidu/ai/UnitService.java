package com.robot.common.baidu.ai;

import com.robot.common.baidu.ai.HttpUtil;
/*
 * unit对话服务
 */
public class UnitService {

/*
    {
        "access_token": "24.34ed2e17cdaf0a5e1300b1d5cefcae35.2592000.1538289006.282335-11753829",
            "session_key": "9mzdCPA2M8aYmZ7mwxhBjzjFIMt66YqE4vQfthIYGAwlSH75sKobXrtHbJYB5apjGJvChBX4yJFrvilP4vRUw79z5Ai72w==",
            "scope": "public brain_all_scope wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi",
            "refresh_token": "25.971dc126ef5b84d608f68cecd9c6b9c8.315360000.1851057006.282335-11753829",
            "session_secret": "778ef48ec178bc1e191ec7372176d9f5",
            "expires_in": 2592000
    }
    */

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    private static String utterance() {
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
        try {
            // 请求参数
            String params = "{" +
                    "\"bot_session\":\"\"," +
                    "\"log_id\":\"9419d9d0-accf-11e8-ba6d-8f3265a63704\"," +
                    "\"request\":{" +
                        "\"bernard_level\":1," +
                        "\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\"," +
                        "\"query\":\"头痛\"," +
                        "\"query_info\":{" +
                            "\"asr_candidates\":[]," +
                            "\"source\":\"KEYBOARD\"," +
                            "\"type\":\"TEXT\"}," +
                        "\"updates\":\"\"," +
                        "\"user_id\":\"UNIT_DEV_10754\"" +
                    "}," +
                    "\"bot_id\":10754," +
                    "\"version\":\"2.0\"" +
                    "}";
            String accessToken = "24.20ffdfa03067aa9b76e1860c2ff8ae94.2592000.1538292109.282335-11753829";
            String result = HttpUtil.post(talkUrl, accessToken, "application/json", params);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        utterance();
    }
}
