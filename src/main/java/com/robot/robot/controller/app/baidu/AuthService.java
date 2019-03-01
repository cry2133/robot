package com.robot.robot.controller.app.baidu;


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "PZc8fPN9yjZ9IudRl0snfOvO";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "gM5I69vcxzGvx8S9IdDsjoloFbWQBj4C";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }



    /**
     * 请求返回内容
     *
     * "refresh_token":"25.257ea1b5d0b7cf62feb7c2c36b39392c.315360000.1860025352.282335-14394119",
     * "expires_in":2592000,
     * "session_key":"9mzdDASel6Ci8jpP0WJZ9auVPUvEsbUf4hAvUXNxLuTJxp1siHDSMH9vx2l0nSLVGEBzarbgrTuV+WJVoF4e8EcwTmciSQ==",
     * "access_token":"24.715078519b28cc8e1ee1572a55c2db9a.2592000.1547257352.282335-14394119",
     * "scope":"vis-faceverify_FACE_V3 audio_voice_assistant_get audio_tts_post public vis-ocr_ocr
     *      nlp_simnet nlp_wordemb nlp_comtag vis-classify_dishes nlp_dnnlm_cn vis-antiporn_antiporn_v2 vis-classify_watermark
     *      brain_ocr_scope vis-classify_car brain_gif_antiporn brain_ocr_general brain_ocr_general_basic brain_ocr_general_enhanced
     *      vis-classify_terror vis-ocr_business_license brain_ocr_webimage brain_nlp_lexer brain_all_scope solution_face brain_ocr_idcard
     *      brain_ocr_driving_license brain_ocr_vehicle_license brain_antiporn brain_antiterror brain_nlp_comment_tag brain_nlp_dnnlm_cn
     *      brain_nlp_word_emb_vec brain_nlp_word_emb_sim brain_nlp_sentiment_classify vis-classify_\u6076\u5fc3\u56fe\u8bc6\u522b\u670d\u52a1
     *      vis-ocr_plate_number vis-classify_animal brain_politician brain_imgquality_general brain_nlp_simnet brain_nlp_depparser
     *      vis-classify_plant brain_solution brain_ocr_plate_number brain_nlp_wordembedding brain_nlp_dnnlm_cn_legacy brain_nlp_simnet_legacy
     *      brain_nlp_comment_tag_legacy brain_watermark brain_ocr_accurate brain_ocr_accurate_basic brain_ocr_receipt brain_ocr_business_license
     *      vis-classify_\u5b9e\u65f6\u68c0\u7d22-\u76f8\u4f3c brain_object_detect brain_realtime_logo brain_realtime_same_hq brain_dish_detect
     *      brain_car_detect brain_realtime_similar brain_animal_classify brain_plant_classify brain_solution_iocr
     *      brain_realtime_product brain_nlp_lexer_custom brain_kg_cognitive vis-faceverify_faceverify_h5-face-liveness
     *      brain_disgust brain_ocr_handwriting brain_nlp_keyword brain_nlp_topic brain_antispam_spam brain_body_analysis
     *      brain_body_attr brain_ocr_vat_invoice brain_advanced_general_classify brain_numbers brain_body_number brain_body_seg
     *      brain_nlp_comment_tag_custom brain_gesture_detect wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi
     *      kaidian_kaidian ApsMisTest_Test\u6743\u9650 vis-classify_flower lpq_\u5f00\u653e cop_helloScope ApsMis_fangdi_permission
     *      smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify",
     * "session_secret":"6ad153b44c089935a894c036d3e04f3c"
     */

}

