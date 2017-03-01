package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Ad;

/**
 *  渠道广告业务逻辑接口
 * @author yy
 *
 */
public interface AdService {
	/** 新增渠道广告 */	
	public boolean addAd(Ad ad) ;	
	/** 删除渠道广告 */	
	public boolean delAd(Integer adId) ;
	/** 更新渠道广告*/	
	public boolean updateAd(Ad ad);
	/** 装载渠道广告 */	
	public Ad loadAd(Integer adId);	
	/** 根据appID装载渠道广告 */	
	public Ad loadAdByAppId(Integer channelId,Integer appId);	
	/** 渠道广告总共数目 */	
	public int countAll();
	/** 根据渠道渠道广告总共数目 */	
	public int countAllByChannelId(Integer channelId);
	/** 分页渠道广告 */
	public List<Ad> browsePagingAd(int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 根据渠道分页渠道广告 */
	public List<Ad> browsePagingAdByChannelId(Integer channelId,int pageNum,int pageSize,String orderName,String orderWay) ;		

}
