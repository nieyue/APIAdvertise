package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Click;

/**
 *  点击数据库接口
 * @author yy
 *
 */
public interface ClickDao {
	/** 新增点击 */	
	public boolean addClick(Click click) ;	
	/** 删除点击 */	
	public boolean delClick(Integer clickId) ;
	/** 更新点击*/	
	public boolean updateClick(Click click);
	/** 装载点击 */	
	public Click loadClick(Integer clickId);	
	/** 根据appID装载点击 */	
	public Click loadClickByAppId(@Param("channelId")Integer channelId,@Param("appid")Integer appid);	
	/** 点击总共数目 */	
	public int countAll();
	/** 根据渠道渠道总共数目 */	
	public int countAllByChannelId(Integer channelId);	
	/** 根据渠道与状态总共数目 */	
	public int countAllByChannelIdAndStatus(@Param("status")String status,@Param("channelId")Integer channelId);	
	/** 分页点击 */
	public List<Click> browsePagingClick(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据渠道分页点击 */
	public List<Click> browsePagingClickByChannelId(@Param("channelId")Integer channelId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据渠道与状态分页点击 */
	public List<Click> browsePagingClickByChannelIdAndStatus(@Param("status")String status,@Param("channelId")Integer channelId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
