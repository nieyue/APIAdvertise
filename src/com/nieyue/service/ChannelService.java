package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Channel;

/**
 * 渠道逻辑层接口
 * @author yy
 *
 */
public interface ChannelService {
	/** 新增渠道 */	
	public boolean addChannel(Channel channel) ;	
	/** 删除渠道  */	
	public boolean delChannel(Integer channelId) ;
	/** 更新渠道 */	
	public boolean updateChannel(Channel channel);
	/** 装载渠道  */	
	public Channel loadChannel(Integer channelId);
	/** 登录渠道 */	
	public Channel loginChannel(String channelName,String password);
	/** 用令牌登录渠道 */	
	public Channel apiTokenChannel(String apiToken);
	/** 渠道 总共数目 */	
	public int countAll();
	/** 渠道 分页信息 */
	public List<Channel> browsePagingChannel(int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 渠道 全部信息 */
	public List<Channel> browseAllChannel(String orderName,String orderWay) ;		
}
