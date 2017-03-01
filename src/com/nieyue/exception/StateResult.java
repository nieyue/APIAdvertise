package com.nieyue.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nieyue.bean.Ad;

import net.sf.json.JSONObject;

/**
 * 返回状态DTO
 * @author yy
 *
 */
@Component
public class StateResult {
	private Integer code;
	private String cm;
	private String msg;
	private List<Object> list;
	public StateResult() {
		super();
	}

	public StateResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}
	/**
	 * 成功
	 * 
	 */
	public static StateResult getSuccess(){
		StateResult sr=new StateResult();
		sr.setCode(200);
		sr.setMsg("success");
		return sr;
	}
	/**
	 * 普通失败
	 * 
	 */
	public static StateResult getFail(){
		StateResult sr=new StateResult();
		sr.setCode(40000);
		sr.setMsg("fail");
		return sr;
	}
	/**
	 * 常规获取返回状态
	 * @param bl 
	 */
	public static StateResult getSR(boolean bl){
		StateResult sr = StateResult.getSuccess();
		if(!bl){
		sr = StateResult.getFail();
		}
		return sr;
	}
	/**
	 * 自定义获取返回状态
	 * @param bl 
	 */
	public static StateResult getSlefSR(Integer code,String msg){
		StateResult sr = new StateResult();
		sr.setCode(code);
		sr.setMsg(msg);
		return sr;
	}
	/**
	 * 自定义获取返回状态
	 * @param bl 
	 */
	public static StateResult getSlefSR(String code,String msg){
		StateResult sr = new StateResult();
		sr.setCode(Integer.valueOf(code));
		sr.setMsg(msg);
		return sr;
	}
	/**
	 * 自定义返回列表
	 * @param bl 
	 */
	public static StateResult getSlefList(String cm,String msg,List<Object> list){
		StateResult sr = new StateResult();
		sr.setCm(cm);
		sr.setMsg(msg);
		sr.setList(list);
		return sr;
	}
	
	public static void main(String[] args) {
		Ad ad=new Ad();
		List<Object> l = new ArrayList<Object>();
		l.add(ad);
		l.add(ad);
		l.add(ad);
		StateResult json = getSlefList("true", "成功", l);
		JSONObject j = JSONObject.fromObject(json);
		System.out.println(j);
		for (int i = 0; i < json.getList().size(); i++) {
			System.out.println(json.getList().get(i).toString());
		}
	}


}
