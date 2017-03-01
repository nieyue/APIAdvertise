package com.nieyue.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import com.thoughtworks.xstream.XStream;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
/**
 * 获取接口访问凭证与票据
 * @author yy
 *
 */
public class WeiXinJSToken {
	/**
	 * http请求json数据
	 * @param url
	 * @return
	 */
    public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return json.toString();  
    }  
	 /**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAccess_token(String appid, String appsecret) {
        //凭证获取(GET)
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
    // 发起GET请求获取凭证
    JSONObject jsonObject=JSONObject.fromObject(loadJson(requestUrl));
    //System.out.println(jsonObject);
   String access_token = null;
    if (null != jsonObject) {
        try {
            access_token = jsonObject.getString("access_token");
           System.out.println(access_token);
        } catch (JSONException e) {
            // 获取token失败
            //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            System.out.println("获取token失败 errcode:{} errmsg:{}"+ jsonObject.getInt("errcode")+ jsonObject.getString("errmsg"));
        }
    }
    return access_token;
       
    }
    /**
     * 调用微信JS接口的临时票据
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    public static String getJsApiTicket(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
        // 发起GET请求获取凭证
        JSONObject jsonObject =JSONObject.fromObject(loadJson(requestUrl));
        String ticket = null;
        if (null != jsonObject) {
            try {
                ticket = jsonObject.getString("ticket");
                System.out.println(ticket);
            } catch (JSONException e) {
                // 获取token失败
                //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                System.out.println("获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+ jsonObject.getString("errmsg"));
            }
        }
        return ticket;
    }
    public static void main(String[] args) {
   
    }
}
