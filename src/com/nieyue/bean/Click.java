package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 点击类
 * @author yy
 *
 */
public class Click implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 点击id
	 */
	private Integer clickId;
	/**
	 * appid
	 */
	private Integer appid;
	
	/**
	 * idfa
	 */
	private String idfa;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * mac
	 */
	private String mac;
	/**
	 * 回调地址
	 */
	private String callback;
	/**
	 *ios 设备的 deviceId
	 */
	private String devid;
	/**
	 * 设备版本
	 */
	private String osver;
	/**
	 * 设备型号
	 */
	private String devtype;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 点击日期
	 */
	private Date updateDate;
	/**
	 * 渠道id
	 */
	private Integer channelId;
	
	public Click() {
		super();
	}

	public Click(Integer clickId, Integer appid, String idfa, String ip, String mac, String callback, String devid,
			String osver, String devtype, String status, Date updateDate, Integer channelId) {
		super();
		this.clickId = clickId;
		this.appid = appid;
		this.idfa = idfa;
		this.ip = ip;
		this.mac = mac;
		this.callback = callback;
		this.devid = devid;
		this.osver = osver;
		this.devtype = devtype;
		this.status = status;
		this.updateDate = updateDate;
		this.channelId = channelId;
	}

	@Override
	public String toString() {
		return "Click [clickId=" + clickId + ", appid=" + appid + ", idfa=" + idfa + ", ip=" + ip + ", mac=" + mac
				+ ", callback=" + callback + ", devid=" + devid + ", osver=" + osver + ", devtype=" + devtype
				+ ", status=" + status + ", updateDate=" + updateDate + ", channelId=" + channelId + "]";
	}

	public Integer getClickId() {
		return clickId;
	}

	public void setClickId(Integer clickId) {
		this.clickId = clickId;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getOsver() {
		return osver;
	}

	public void setOsver(String osver) {
		this.osver = osver;
	}

	public String getDevtype() {
		return devtype;
	}

	public void setDevtype(String devtype) {
		this.devtype = devtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}





	
}
