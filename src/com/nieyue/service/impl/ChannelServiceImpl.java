package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Channel;
import com.nieyue.dao.ChannelDao;
import com.nieyue.service.ChannelService;
@Service("channelService")
public class ChannelServiceImpl implements ChannelService{
	@Resource
	ChannelDao channelDao;

	@Override
	public boolean addChannel(Channel channel) {
		channel.setCreateDate(new Date());
		channel.setLastLoginDate(new Date());
		boolean b = channelDao.addChannel(channel);
		return b;
	}

	@Override
	public boolean delChannel(Integer channelId) {
		boolean b = channelDao.delChannel(channelId);
		return b;
	}

	@Override
	public boolean updateChannel(Channel channel) {
		boolean b = channelDao.updateChannel(channel);
		return b;
	}

	@Override
	public Channel loadChannel(Integer channelId) {
		Channel r = channelDao.loadChannel(channelId);
		return r;
	}

	@Override
	public int countAll() {
		int c = channelDao.countAll();
		return c;
	}

	@Override
	public List<Channel> browsePagingChannel(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Channel> l = channelDao.browsePagingChannel(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public Channel loginChannel(String channelName, String password) {
		
		Channel a = channelDao.loginChannel(channelName, password);
		a.setLastLoginDate(new Date());
		channelDao.updateChannel(a);
		return a;
	}


	@Override
	public List<Channel> browseAllChannel(String orderName, String orderWay) {
			List<Channel> l = channelDao.browseAllChannel(orderName, orderWay);
			return l;
	}

	@Override
	public Channel apiTokenChannel(String apiToken) {
		Channel c = channelDao.apiTokenChannel(apiToken);
		return c;
	}

}
