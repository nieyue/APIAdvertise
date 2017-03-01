package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Ad;

/**
 *  渠道广告数据库接口
 * @author yy
 *
 */
public interface AdDao {
	/** 新增渠道广告 */	
	public boolean addAd(Ad ad) ;	
	/** 删除渠道广告 */	
	public boolean delAd(Integer adId) ;
	/** 更新渠道广告*/	
	public boolean updateAd(Ad ad);
	/** 装载渠道广告 */	
	public Ad loadAd(Integer adId);	
	/** 根据appID装载渠道广告 */	
	public Ad loadAdByAppId(@Param("channelId")Integer channelId,@Param("appId")Integer appId);	
	/** 渠道广告总共数目 */	
	public int countAll();	
	/** 根据渠道渠道广告总共数目 */	
	public int countAllByChannelId(Integer channelId);	
	/** 分页渠道广告 */
	public List<Ad> browsePagingAd(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据渠道分页渠道广告 */
	public List<Ad> browsePagingAdByChannelId(@Param("channelId")Integer channelId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
