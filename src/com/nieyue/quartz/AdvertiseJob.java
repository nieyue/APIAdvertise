package com.nieyue.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.nieyue.bean.Ad;
import com.nieyue.bean.Advertise;
import com.nieyue.bean.Channel;
import com.nieyue.service.AdService;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.ChannelService;
import com.nieyue.util.HttpClientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 广告位工作
 * @author yy
 *
 */
public class AdvertiseJob {
	@Resource
	private AdvertiseService advertiseService;
	@Resource
	private ChannelService channelService;
	@Resource
	private AdService adService;
	/**
	 * 每隔20分钟调用一次
	 */
	public void doGetApps(){
		String url="http://ruikes.baoerpai.com/doGetApps?api_token=CB316B312E1BB6B7710ACFEDD42BD522";
		String s = null;
		boolean flag=false;//抓取添加成功，默认false
		try {
			s = HttpClientUtil.doGet(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=JSONObject.fromObject(s);
		JSONArray jsonarry = (JSONArray) json.get("adList");
		for (int i = 0; i < jsonarry.size(); i++) {
			Advertise advertise=new Advertise();
			JSONObject a = (JSONObject) jsonarry.get(i);
			advertise=(Advertise) JSONObject.toBean(a, Advertise.class);
			advertise.setUpdateDate(new Date());
			Advertise isadvertise = advertiseService.loadAdvertiseByAppId(advertise.getAppId());
			if(isadvertise==null || isadvertise.equals("")){
				 flag = advertiseService.addAdvertise(advertise);				
			}
			//添加成功，遍历所有渠道主，添加产品
			if(flag){
				List<Channel> clist = channelService.browseAllChannel("channel_id","desc");
				if(clist.size()>0){
				for (int j = 0; j < clist.size(); j++) {
					Channel c = clist.get(j);
					//根据渠道id添加ad
					Ad ad=new Ad();
					ad.setAppId(advertise.getAppId());
					ad.setAdName(advertise.getAdName());
					
					ad.setAppUrl("http://apiadvertise.yayao8.com/click/send?channel_id="+c.getChannelId()+"&appid="+advertise.getAppId());//修改为我们的
					ad.setBundleId(advertise.getBundleId());
					ad.setChannelId(c.getChannelId());
					ad.setCountry(advertise.getCountry());
					ad.setCurrency(advertise.getCurrency());
					ad.setIconUrl(advertise.getIconUrl());
					ad.setPreviewUrl(advertise.getPreviewUrl());
					if(advertise.getPrice()<=0.5){
						ad.setPrice(advertise.getPrice());
					}else{
						ad.setPrice(advertise.getPrice()-0.5);//修改为默认值减少到0.5
					}
					ad.setTaskDemands(advertise.getTaskDemands());
					ad.setTaskNum(advertise.getTaskNum());
					ad.setUpdateDate(advertise.getUpdateDate());
					adService.addAd(ad);
				}
				}
			}
		}
	}
	
}
