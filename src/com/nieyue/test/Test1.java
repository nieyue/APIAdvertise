package com.nieyue.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.nieyue.bean.Ad;
import com.nieyue.bean.AdN;
import com.nieyue.bean.Advertise;
import com.nieyue.util.HttpClientUtil;
import com.nieyue.util.URLAnalysis;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test1 {
	void aaa() throws Exception{
		String url="http://ruikes.baoerpai.com/doGetApps?api_token=CB316B312E1BB6B7710ACFEDD42BD522";
		String s = HttpClientUtil.doGet(url);
		JSONObject json=JSONObject.fromObject(s);
		JSONArray jsonarry = (JSONArray) json.get("adList");
		for (int i = 0; i < jsonarry.size(); i++) {
			Advertise advertise=new Advertise();
			JSONObject a = (JSONObject) jsonarry.get(i);
			advertise=(Advertise) JSONObject.toBean(a, Advertise.class);
			advertise.setUpdateDate(new Date());
			System.out.println(advertise.toString());
		}
	}
	public static void main(String[] args) throws Exception {
		Ad ad = new Ad();
		ad.setAdId(111);
		ad.setAdName("adn");
		ad.setAppId(223);
		ad.setUpdateDate(new Date());
		AdN adN=new AdN();
		BeanUtils.copyProperties(ad, adN);
		System.out.println(adN);
		String url="http://apiadvertise.yayao8.com/click/accept?click_id=444&appid=555&success=false";
		System.out.println(URLEncoder.encode(url,"utf-8"));
		System.out.println(URLDecoder.decode(URLEncoder.encode(url,"utf-8"),"utf-8"));
		System.out.println(String.valueOf(false));
		URLAnalysis urlAnalysis = new URLAnalysis();
		urlAnalysis.analysis(url);
		String channel_id = urlAnalysis.getParam("channel_id");
		String callbackappid = urlAnalysis.getParam("appid");
		String success = urlAnalysis.updateParam("success", String.valueOf(true));
		System.out.println(url);
	}
}
