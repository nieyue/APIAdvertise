package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道类
 * @author yy
 *
 */
public class Channel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 渠道id
	 */
	private Integer channelId;
	
	/**
	 * 渠道姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String cellPhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * api_token
	 */
	private String apiToken;
	/**
	 * 账号创建时间
	 */
	private Date createDate;
	/**
	 * 最新登录时间
	 */
	private Date lastLoginDate;
	
	public Channel() {
		super();
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Channel(Integer channelId, String name, String cellPhone, String email, String password, String apiToken,
			Date createDate, Date lastLoginDate) {
		super();
		this.channelId = channelId;
		this.name = name;
		this.cellPhone = cellPhone;
		this.email = email;
		this.password = password;
		this.apiToken = apiToken;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}


	

}
