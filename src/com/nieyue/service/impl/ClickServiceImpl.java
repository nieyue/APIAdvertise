package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Click;
import com.nieyue.dao.ClickDao;
import com.nieyue.service.ClickService;
@Service("clickService")
public class ClickServiceImpl implements ClickService{
	@Resource
	ClickDao clickDao;
	@Override
	public boolean addClick(Click click) {
		click.setUpdateDate(new Date());
		click.setStatus("已点击");
		boolean b = clickDao.addClick(click);
		return b;
	}

	@Override
	public boolean delClick(Integer clickId) {
		boolean b = clickDao.delClick(clickId);
		return b;
	}

	@Override
	public boolean updateClick(Click click) {
		click.setUpdateDate(new Date());
		boolean b =clickDao.updateClick(click);
		return b;
	}

	@Override
	public Click loadClick(Integer clickId) {
		 Click p = clickDao.loadClick(clickId);
		return p;
	}

	@Override
	public int countAll() {
		int p = clickDao.countAll();
		return p;
	}


	@Override
	public List<Click> browsePagingClick(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Click> l = clickDao.browsePagingClick(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public Click loadClickByAppId(Integer channelId,Integer appid) {
		Click a = clickDao.loadClickByAppId(channelId,appid);
		return a;
	}

	@Override
	public List<Click> browsePagingClickByChannelId(Integer channelId, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Click> l = clickDao.browsePagingClickByChannelId(channelId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public int countAllByChannelId(Integer channelId) {
		int c = clickDao.countAllByChannelId(channelId);
		return c;
	}

	@Override
	public List<Click> browsePagingClickByChannelIdAndStatus(String status, Integer channelId, int pageNum,
			int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Click> l = clickDao.browsePagingClickByChannelIdAndStatus(status, channelId, pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public int countAllByChannelIdAndStatus(String status, Integer channelId) {
		int c = clickDao.countAllByChannelIdAndStatus(status, channelId);
		return c;
	}

}
