package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Ad;
import com.nieyue.dao.AdDao;
import com.nieyue.service.AdService;
@Service("adService")
public class AdServiceImpl implements AdService{
	@Resource
	AdDao adDao;
	@Override
	public boolean addAd(Ad ad) {
		ad.setUpdateDate(new Date());
		boolean b = adDao.addAd(ad);
		return b;
	}

	@Override
	public boolean delAd(Integer adId) {
		boolean b = adDao.delAd(adId);
		return b;
	}

	@Override
	public boolean updateAd(Ad ad) {
		ad.setUpdateDate(new Date());
		boolean b =adDao.updateAd(ad);
		return b;
	}

	@Override
	public Ad loadAd(Integer adId) {
		 Ad p = adDao.loadAd(adId);
		return p;
	}

	@Override
	public int countAll() {
		int p = adDao.countAll();
		return p;
	}


	@Override
	public List<Ad> browsePagingAd(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Ad> l = adDao.browsePagingAd(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public Ad loadAdByAppId(Integer channelId,Integer appId) {
		Ad a = adDao.loadAdByAppId(channelId,appId);
		return a;
	}

	@Override
	public List<Ad> browsePagingAdByChannelId(Integer channelId, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Ad> l = adDao.browsePagingAdByChannelId(channelId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public int countAllByChannelId(Integer channelId) {
		int c = adDao.countAllByChannelId(channelId);
		return c;
	}

}
