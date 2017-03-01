package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告类
 * @author yy
 *
 */
public class Advertise implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广告索引id
	 */
	private Integer advertiseId;
	/**
	 * 广告id
	 */
	private Integer appId;
	
	/**
	 * 广告名称
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
	 * 货币
	 */
	private String currency;
	/**
	 *国家
	 */
	private String country;
	/**
	 * 图标
	 */
	private String iconUrl;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 投放链接
	 */
	private String appUrl;
	/**
	 * 预览链接
	 */
	private String previewUrl;
	/**
	 * 任务数量
	 */
	private Integer taskNum;
	/**
	 * 获取日期
	 */
	private Date updateDate;
	
	public Advertise() {
		super();
	}

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public Integer getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getAdvertiseId() {
		return advertiseId;
	}

	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
	}

	public Advertise(Integer advertiseId, Integer appId, String adName, String bundleId, String taskDemands,
			String currency, String country, String iconUrl, Double price, String appUrl, String previewUrl,
			Integer taskNum, Date updateDate) {
		super();
		this.advertiseId = advertiseId;
		this.appId = appId;
		this.adName = adName;
		this.bundleId = bundleId;
		this.taskDemands = taskDemands;
		this.currency = currency;
		this.country = country;
		this.iconUrl = iconUrl;
		this.price = price;
		this.appUrl = appUrl;
		this.previewUrl = previewUrl;
		this.taskNum = taskNum;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Advertise [advertiseId=" + advertiseId + ", appId=" + appId + ", adName=" + adName + ", bundleId="
				+ bundleId + ", taskDemands=" + taskDemands + ", currency=" + currency + ", country=" + country
				+ ", iconUrl=" + iconUrl + ", price=" + price + ", appUrl=" + appUrl + ", previewUrl=" + previewUrl
				+ ", taskNum=" + taskNum + ", updateDate=" + updateDate + "]";
	}


	
}
