package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 无价格渠道广告接口类
 * @author yy
 *
 */
public class AdN implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 渠道广告id
	 */
	private Integer appId;
	
	/**
	 * 渠道广告名称
	 */
	private String adName;
	/**
	 * 包名bundleId
	 */
	private String bundleId;
	/**
	 * 任务要求
	 */
	private String taskDemands;
	/**
	 *国家
	 */
	private String country;
	/**
	 * 图标
	 */
	private String iconUrl;
	/**
	 * 投放链接
	 */
	private String appUrl;
	/**
	 * 任务数量
	 */
	private Integer taskNum;
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getBundleId() {
		return bundleId;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public String getTaskDemands() {
		return taskDemands;
	}
	public void setTaskDemands(String taskDemands) {
		this.taskDemands = taskDemands;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public Integer getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}
	public AdN(Integer appId, String adName, String bundleId, String taskDemands, String country, String iconUrl,
			String appUrl, Integer taskNum) {
		super();
		this.appId = appId;
		this.adName = adName;
		this.bundleId = bundleId;
		this.taskDemands = taskDemands;
		this.country = country;
		this.iconUrl = iconUrl;
		this.appUrl = appUrl;
		this.taskNum = taskNum;
		
	}
	public AdN() {
		super();
	}
	

	
}
