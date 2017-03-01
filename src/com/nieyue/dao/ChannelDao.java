package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Channel;

/**
 * 渠道数据库接口
 * @author yy
 *
 */
public interface ChannelDao {
	/** 新增渠道 */	
	public boolean addChannel(Channel channel) ;	
	/** 删除渠道  */	
	public boolean delChannel(Integer channelId) ;
	/** 更新渠道 */	
	public boolean updateChannel(Channel channel);
	/** 装载渠道  */	
	public Channel loadChannel(Integer channelId);
	/** 登录渠道 */	
	public Channel loginChannel(@Param("channelName")String channelName,@Param("password")String password);
	/** 用令牌登录渠道 */	
	public Channel apiTokenChannel(String apiToken);
	/** 渠道 总共数目 */	
	public int countAll();
	/** 渠道 分页信息 */
	public List<Channel> browsePagingChannel(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 渠道 全部信息 */
	public List<Channel> browseAllChannel(@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
