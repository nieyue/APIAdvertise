package com.nieyue.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nieyue.bean.Ad;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 成功的list
 * @author yy
 *
 */
@Component
public class SuccessList {
	private boolean success;
	private String msg;
	public SuccessList() {
		super();
	}

	

	public boolean getSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	/**
	 * 自定义返回列表
	 * @param bl 
	 */
	public static String getSlefList(boolean success,String msg,String listfront,List<Object> list){
		SuccessList sr = new SuccessList();
		sr.setSuccess(success);
		sr.setMsg(msg);
		JSONObject json = JSONObject.fromObject(sr);
		if(success){
		JSONArray j = JSONArray.fromObject(list);
		 json.put(listfront, j);
		}
		return json.toString();
	}
	/**
	 * 自定义返回列表
	 * @param bl 
	 */
	public static String getSlefList(boolean success,String listfront,List<?> list){
		SuccessList sr = new SuccessList();
		sr.setSuccess(success);
		JSONObject json;
		if(success){
		sr.setMsg("成功");
		 json = JSONObject.fromObject(sr);
			JSONArray j = JSONArray.fromObject(list);
			json.put(listfront, j);
		}else{
			sr.setMsg("失败");
			 json = JSONObject.fromObject(sr);
		}
		return json.toString();
	}
	/**
	 * 自定义返回列表
	 * @param bl 
	 */
	public static String getSlefList(boolean success){
		SuccessList sr = new SuccessList();
		sr.setSuccess(success);
		JSONObject json;
		if(success){
			sr.setMsg("成功");
			json = JSONObject.fromObject(sr);
		}else{
			sr.setMsg("失败");
			json = JSONObject.fromObject(sr);
		}
		return json.toString();
	}
	
	public static void main(String[] args) {
		Ad ad=new Ad();
		List<Object> adList = new ArrayList<Object>();
		adList.add(ad);
		adList.add(ad);
		adList.add(ad);
		String json = getSlefList(true,"adList", adList);
		//String json = getSlefList(false,"adList", adList);
		
		System.out.println(json);
//		for (int i = 0; i < json.getList().size(); i++) {
//			System.out.println(json.getList().get(i).toString());
//		}
	}



}
