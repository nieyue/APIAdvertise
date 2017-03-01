package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Click;

/**
 *  点击业务逻辑接口
 * @author yy
 *
 */
public interface ClickService {
	/** 新增点击 */	
	public boolean addClick(Click click) ;	
	/** 删除点击 */	
	public boolean delClick(Integer clickId) ;
	/** 更新点击*/	
	public boolean updateClick(Click click);
	/** 装载点击 */	
	public Click loadClick(Integer clickId);	
	/** 根据appID装载点击 */	
	public Click loadClickByAppId(Integer channelId,Integer appid);	
	/** 点击总共数目 */	
	public int countAll();
	/** 根据渠道渠道总共数目 */	
	public int countAllByChannelId(Integer channelId);
	/** 根据渠道与状态总共数目 */	
	public int countAllByChannelIdAndStatus(String status,Integer channelId);
	/** 分页点击 */
	public List<Click> browsePagingClick(int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 根据渠道分页点击 */
	public List<Click> browsePagingClickByChannelId(Integer channelId,int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 根据渠道与状态分页点击 */
	public List<Click> browsePagingClickByChannelIdAndStatus(String status,Integer channelId,int pageNum,int pageSize,String orderName,String orderWay) ;		

}
