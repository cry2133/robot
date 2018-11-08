package com.robot.common.baidu.ai;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * UNIT 2.0 API请求示例代码
 * @author 小帅丶
 *
 */
public class UnitV2Sample {
    public static String UNIT_URL ="https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
    public static void main(String[] args) throws Exception {
        String result = getUNITV2Result("场景ID", "本轮对话内容", "自己应用获取的AccessToken");
        System.out.println(result);
    }
    /**
     * UNIT 2.0 API请求方法 只需要场景id 和对话内容参数 大家可以根据需要稍作修改
     * @param bot_id 场景id
     * @param query 本轮对话内容
     * @param access_token 请求接口所需的access_token
     * @return String
     * @throws Exception
     */
    public static String getUNITV2Result(String bot_id,String query,String access_token) throws Exception{
        UnitV2RequestBean bean = new UnitV2RequestBean();
        bean.setVersion("2.0");
        bean.setBot_id(bot_id);
        bean.setLog_id("XS"+System.currentTimeMillis());
        UnitV2RequestBean.Request request = new UnitV2RequestBean.Request();
        request.setUser_id("XS0001");//测试设置 大家请自行更改
        request.setQuery(query);
        UnitV2RequestBean.QueryInfo query_info = new UnitV2RequestBean.QueryInfo();
        query_info.setType("TEXT");
        query_info.setSource("KEYBOARD");
        request.setQuery_info(query_info);
        request.setBernard_level(0);
        //希望传给bot的本地信息
        UnitV2RequestBean.ClientSession client_session = new UnitV2RequestBean.ClientSession();
        client_session.setClient_results("hhhh");
        List candidate_options = new ArrayList();
        candidate_options.add(0, "123");
        candidate_options.add(1, "456");
        client_session.setCandidate_options(candidate_options);
        /**
         * ClientSession所需要的是字符串类型 内容为json格式
         * "client_session": "{\"candidate_options\": [\"123\", \"456\"], \"client_results\": \"hhhh\"}"
         */
        String client_sessionparam = JSONObject.toJSONString(client_session);
        request.setClient_session(client_sessionparam);
        bean.setRequest(request);
        bean.setBot_session("");
        String jsonparam = JSONObject.toJSONString(bean);
        System.out.println("参数"+jsonparam);
        String result = HttpUtil.post(UNIT_URL, access_token, "application/json", jsonparam);
        return result;
    }
}