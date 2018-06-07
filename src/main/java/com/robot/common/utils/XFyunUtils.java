package com.robot.common.utils;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.logging.Logger;

import com.alibaba.fastjson.JSONObject;
import com.robot.common.domain.XFyunReturn;


/**
 * 
 * AIUI WebAPI V2接口调用示例  --讯飞语音云--
 * 
 * 运行方法：直接运行 main()
 * 
 * 结果： 控制台输出接口返回值信息
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年5月21日
 */
public class XFyunUtils {
	private static Logger logger = Logger.getLogger("XFyunUtils");
	
	private static final String URL = "http://openapi.xfyun.cn/v2/aiui";
	private static final String APPID = "5b0264f2";
	private static final String API_KEY = "df1593497a0b4c5d9cfbdcc2011089bd";
	//private static final String DATA_TYPE = "audio";
	private static final String DATA_TYPE = "text";
	private static final String SCENE = "main";
	private static final String SAMPLE_RATE = "16000";
	private static final String AUTH_ID = "26b37441ed7a3b8e13c40598a72f5cd2";
	private static final String AUE = "raw";
	private static final String FILE_PATH = "";
	
	public static void main(String[] args) throws IOException, ParseException {
		XFyunReturn xf = runChat("好好睡觉");
		System.out.println(xf.getText());
		System.out.println(xf.getCode());
	}
	
	/**
	 * 提交问题，返回答案
	 */
	public static XFyunReturn runChat(String question) throws IOException, ParseException{
		Map<String, String> header = buildHeader();
		//byte[] dataByteArray = readFile(FILE_PATH);
		//String result = httpPost(URL, header, dataByteArray);
		byte[] qs= question.getBytes();
		String result = httpPost(URL, header, qs);
        JSONObject jsonObject = JSONObject.parseObject(result); 
        XFyunReturn xf = new XFyunReturn();
        /*for (Object map : jsonObject.entrySet()){  
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue()); 
        } */
        if (jsonObject != null) {
        	JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
			JSONObject intent = JSONObject.parseObject(data.getString("intent"));
			String answer = intent.getString("answer");
			String getText = intent.getString("text");
			if(getText!=null && getText!="" && question.equals(getText) && answer==null){   //讯飞返回success 但是没有答案
				xf.setText(null);
				String sid = jsonObject.getString("sid");
				logger.info("----------讯飞查询不到答案  sid: "+sid+"----------有异常或疑问可以用 sid 找讯飞咨询----------");   
			}else{
				/**
				 * 找到答案
				 */
				JSONObject ans = JSONObject.parseObject(answer);
				String text = ans.getString("text");
				xf.setText(text);
			}
			xf.setCode(jsonObject.getString("code"));
			xf.setDesc(jsonObject.getString("desc"));
		}
		return xf;
	}

	/**
	 * 访问接口必要的四个参数，以下为官方提供
	 */
	private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";		
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

		Map<String, String> header = new HashMap<String, String>();
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		return header;
	}
	
	/**
	 * 读取音频文件
	 */
	private static byte[] readFile(String filePath) throws IOException {
		InputStream in = new FileInputStream(filePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		byte[] data = out.toByteArray();
		in.close();
		return data;
	}
	
	/**
	 * 访问讯飞接口
	 */
	private static String httpPost(String url, Map<String, String> header, byte[] body) {
		String result = "";
		BufferedReader in = null;
		OutputStream out = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			
			//connection.setConnectTimeout(20000);
			//connection.setReadTimeout(20000);
			try {
				out = connection.getOutputStream();
				out.write(body);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
