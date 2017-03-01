package com.nieyue.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.Advertise;
import com.nieyue.bean.Click;
import com.nieyue.exception.StateResult;
import com.nieyue.exception.SuccessList;
import com.nieyue.service.AdService;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.ChannelService;
import com.nieyue.service.ClickService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.HttpClientUtil;
import com.nieyue.util.URLAnalysis;
import com.nieyue.util.UploaderPath;


/**
 * 点击控制类
 * @author yy
 *
 */
@Controller("clickController")
@RequestMapping("/click")
public class ClickController {
	@Resource
	private ClickService clickService;
	@Resource
	private ChannelService channelService;
	@Resource
	private AdvertiseService advertiseService;
	@Resource
	private AdService adService;
	
	/**
	 * 点击分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Click> browsePagingClick(
			@RequestParam(value="status",defaultValue="all",required=false)String status,
			@RequestParam(value="channel_id",defaultValue="0",required=false)Integer channelId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="click_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Click> list = new ArrayList<Click>();
			if(channelId==0){//不分渠道
				list= clickService.browsePagingClick(pageNum, pageSize, orderName, orderWay);
			}else{
				if(status.equals("all")){//不分状态
					list= clickService.browsePagingClickByChannelId(channelId, pageNum, pageSize, orderName, orderWay);
				}else{
					list= clickService.browsePagingClickByChannelIdAndStatus(status,channelId, pageNum, pageSize, orderName, orderWay);
					
				}
				
			}
			return list;
	}
	/**
	 * 点击增加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addClick(@ModelAttribute Click click, HttpSession session) {
		boolean am = clickService.addClick(click);
		return StateResult.getSR(am);
	}
	/**
	 * 点击删除
	 * @return
	 */
	@RequestMapping(value = "/{clickId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delClick(@PathVariable("clickId") Integer clickId,HttpSession session)  {
		boolean dm = clickService.delClick(clickId);
		return StateResult.getSR(dm);
	}
	/**
	 * 点击浏览数量countAll
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count =clickService.countAll();
		return count;
	}
	/**
	 * 点击浏览数量countAllByChannelId
	 * @return
	 */
	@RequestMapping(value = "/count/{channelId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByChannelId(@PathVariable("channelId") Integer channelId,HttpSession session)  {
		int count =clickService.countAllByChannelId(channelId);
		return count;
	}
	/**
	 * 点击浏览数量countAllByChannelIdAndStatus
	 * @return
	 */
	@RequestMapping(value = "/count/{channelId}/{status}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByChannelIdAndStatus(@PathVariable("channelId") Integer channelId,@PathVariable("status") String status,HttpSession session)  {
		int count =clickService.countAllByChannelIdAndStatus(status, channelId);
		return count;
	}
	/**
	 * 点击单个加载
	 * @return
	 */
	@RequestMapping(value = "/{clickId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Click loadClick(@PathVariable("clickId") Integer clickId,HttpSession session)  {
		Click click=new Click();
		click = clickService.loadClick(clickId);
		return click;
	}
	/**
	 * 接受渠道的接口地址,返回给上级供应商
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value = "/send", method = {RequestMethod.GET,RequestMethod.POST})
	public  String sendClick(
			@RequestParam(value="channel_id")Integer channelId,
			@RequestParam(value="appid")Integer appid,
			@RequestParam(value="mac")String mac,
			@RequestParam(value="idfa")String idfa,
			@RequestParam(value="ip")String ip,
			@RequestParam(value="callback")String callback,
			@RequestParam(value="devid",defaultValue="无",required=false) String devid,
			@RequestParam(value="osver",defaultValue="无",required=false) String osver,
			@RequestParam(value="devtype",defaultValue="无",required=false) String devtype,
			HttpSession session) throws Exception  {
		Click click=new Click();
		click.setAppid(appid);
		click.setCallback(callback);
		click.setChannelId(channelId);
		click.setIdfa(idfa);
		click.setIp(ip);
		click.setMac(mac);
		click.setDevid(devid);
		click.setOsver(osver);
		click.setDevtype(devtype);
		boolean flag = clickService.addClick(click);
		if(flag){
			Advertise advertise = advertiseService.loadAdvertiseByAppId(appid);
			URLAnalysis urlAnalysis = new URLAnalysis();
			urlAnalysis.analysis(advertise.getAppUrl());
			String cid = urlAnalysis.getParam("cid");
			String redirectUrl="http://ruikes.baoerpai.com/adClick?cid="
					+cid+"&appid="+appid+"&mac="+mac+"&idfa="+idfa+"&ip="+ip+"&devid="+devid+"&osver="+osver+"&devtype="+devtype+
					"&callback="+URLEncoder.encode("http://apiadvertise.yayao8.com/click/accept?click_id="+click.getClickId()+"&appid="+appid+"&success=true","utf-8");
			return redirectUrl;
		}
		return SuccessList.getSlefList(false);
	}
	/**
	 * 接受上级供应商的返回数据,发送给渠道
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value = "/accept", method = {RequestMethod.GET,RequestMethod.POST})
	public String acceptClick(
			@RequestParam(value="click_id")Integer clickId,
			@RequestParam(value="appid")Integer appid,
			@RequestParam(value="success")Boolean success,
			HttpSession session) throws UnsupportedEncodingException  {
		Click click = clickService.loadClick(clickId);
		if(success){
			click.setStatus("成功");
		}else{
			click.setStatus("失败");
		}
		clickService.updateClick(click);
		
		String cb = URLDecoder.decode(click.getCallback(),"utf-8");
		URLAnalysis urlAnalysis = new URLAnalysis();
		urlAnalysis.analysis(cb);
		//String channel_id = urlAnalysis.getParam("channel_id");
		String ss = urlAnalysis.getParam("success");
		if(ss!=null && !ss.equals("")){
		cb = urlAnalysis.updateURL(cb, "success", String.valueOf(success));
		}
		//Ad ad = adService.loadAdByAppId(Integer.valueOf(channel_id), Integer.valueOf(callbackappid));
		
//		if(ad.getTaskNum()>=1){
//			ad.setTaskNum(ad.getTaskNum()-1);
//		}else{
//		
//		}
		String redirectUrl=cb;
			return redirectUrl;
	}
	/**
	 * 图片修改
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String updateClickImg(
			HttpSession session,
			@RequestParam("img") MultipartFile file,
			@RequestParam("oldImg")String oldImg) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.updateFormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG), imgdir, oldImg);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
	/**
	 * 图片增加
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String addClickImg(
			@RequestParam("img") MultipartFile file,
			HttpSession session ) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.FormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG),imgdir);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
}
