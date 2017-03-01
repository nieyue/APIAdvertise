package com.nieyue.test;

import java.net.URLEncoder;

import com.nieyue.util.HttpClientUtil;

public class Test2 {
public static void main(String[] args) throws Exception {
	String callback="http://apiadvertise.yayao8.com/click/accept?click_id=1048&appid=24294&success=true";
	String doGet="http://ruikes.baoerpai.com/adClick?cid=283&appid=24294&mac=020000000000&idfa=B2F10C7B-68AC-4D6D-AE38-8C7429FB7D23&ip=-1233161520&devid=无&osver=8.1.3&devtype=iPhone7,2&callback="+URLEncoder.encode(callback,"utf-8");
	String s = HttpClientUtil.doGet(doGet);
	System.out.println(s);
}
}
