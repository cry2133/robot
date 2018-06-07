package com.robot.common.utils;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.robot.common.domain.TuLingReturn;

import net.sf.json.JSONObject;



public class TuLingUtils {
	
	private static final String TULINGKEY = "efa0ce80eaed4056b9f88d484c66ca12";
	private static final String TULINGURL = "http://www.tuling123.com/openapi/api";
	
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static JSONObject doGetStr(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    CloseableHttpResponse response = null;
	    JSONObject jsonObject = null;
	    try {
	    	response = httpclient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
	        EntityUtils.consume(entity);
	    } catch(Exception e){
	    	e.printStackTrace();
	    } finally {
	        try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		return jsonObject;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param ourStr
	 * @return
	 * @throws Exception 
	 */
	private static JSONObject doPostStr(String url, JSONObject speechJson) throws Exception{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
          
        StringEntity entity = new StringEntity(speechJson.toString(),"UTF-8");
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
        httpPost.setEntity(entity);
	    CloseableHttpResponse response = httpclient.execute(httpPost);
	    JSONObject jsonObject = null;

	    try {
	        HttpEntity entity2 = response.getEntity();
	        if (entity2 != null) {
	        	String result = EntityUtils.toString(entity2, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
	        EntityUtils.consume(entity2);
	    } finally {
	        response.close();
	    }
		return jsonObject;
	}
	
	
	
	
	
	public static TuLingReturn postTalk(String info) throws Exception {
		JSONObject params = new JSONObject();
		params.put("key", TULINGKEY);
		params.put("info", info);
		params.put("userid", "123456");
		
		TuLingReturn tu = new TuLingReturn();
		JSONObject jsonObject = doPostStr(TULINGURL, params);
		if (jsonObject != null) {
			tu.setCode(jsonObject.getString("code"));
			tu.setText(jsonObject.getString("text"));
		}
		return tu;
	}
	
}
