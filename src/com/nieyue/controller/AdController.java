package com.nieyue.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.Ad;
import com.nieyue.bean.AdN;
import com.nieyue.bean.AdP;
import com.nieyue.bean.Channel;
import com.nieyue.exception.StateResult;
import com.nieyue.exception.SuccessList;
import com.nieyue.service.AdService;
import com.nieyue.service.ChannelService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.UploaderPath;

import net.sf.json.JSONObject;


/**
 * 渠道广告控制类
 * @author yy
 *
 */
@Controller("adController")
@RequestMapping("/ad")
public class AdController {
	@Resource
	private AdService adService;
	@Resource
	private ChannelService channelService;
	
	/**
	 * 渠道广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Ad> browsePagingAd(
			@RequestParam(value="channel_id",defaultValue="0",required=false)Integer channelId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="ad_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Ad> list = new ArrayList<Ad>();
			if(channelId==0){
				list= adService.browsePagingAd(pageNum, pageSize, orderName, orderWay);
			}else{
				list= adService.browsePagingAdByChannelId(channelId,pageNum, pageSize, orderName, orderWay);
				
			}
			return list;
	}
	 @InitBinder
	    public void bindingPreparation(WebDataBinder binder) {

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
	        binder.registerCustomEditor(Date.class, orderDateEditor);
	    }
	/**
	 * 渠道广告分页浏览 
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return 有价格的adlist
	 */
	@RequestMapping(value = "/plist", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody JSONObject browsePagingByChannelIdAdPlist(
			@RequestParam(value="api_token")String apiToken,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="ad_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Ad> list = new ArrayList<Ad>();
		List<AdP> list2 = new ArrayList<AdP>();
		
		Channel channel = channelService.apiTokenChannel(apiToken);
		String result;
		if(channel!=null && !channel.equals("") ){
			list= adService.browsePagingAdByChannelId(channel.getChannelId(),pageNum, pageSize, orderName, orderWay);
			for (int i = 0; i < list.size(); i++) {
				Ad ad = list.get(i);
				AdP adP=new AdP();
				BeanUtils.copyProperties(ad, adP);
				list2.add(adP);
			}
			result = SuccessList.getSlefList(true, "adList", list2);
		}else{
			result = SuccessList.getSlefList(false);
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 渠道广告分页浏览 
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return 无价格的adlist
	 */
	@RequestMapping(value = "/nlist", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody JSONObject browsePagingByChannelIdAdNlist(
			@RequestParam(value="api_token")String apiToken,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="ad_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Ad> list = new ArrayList<Ad>();
		List<AdN> list2 = new ArrayList<AdN>();
		
		Channel channel = channelService.apiTokenChannel(apiToken);
		String result;
		if(channel!=null && !channel.equals("") ){
			list= adService.browsePagingAdByChannelId(channel.getChannelId(),pageNum, pageSize, orderName, orderWay);
			for (int i = 0; i < list.size(); i++) {
				Ad ad = list.get(i);
				AdN adN=new AdN();
				BeanUtils.copyProperties(ad, adN);
				list2.add(adN);
			}
			result = SuccessList.getSlefList(true, "adList", list2);
		}else{
			result = SuccessList.getSlefList(false);
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 渠道广告增加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAd(@ModelAttribute Ad ad, HttpSession session) {
		boolean am = adService.addAd(ad);
		return StateResult.getSR(am);
	}
	/**
	 * 修改渠道广告金额
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/update/{adId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAd(@PathVariable(value="adId")Integer adId,@RequestParam(value="price")Double price, HttpSession session) {
		Ad oldAd = adService.loadAd(adId);
		oldAd.setPrice(price);
		boolean am = adService.updateAd(oldAd);
		return StateResult.getSR(am);
	}
	/**
	 * 渠道广告删除
	 * @return
	 */
	@RequestMapping(value = "/{adId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAd(@PathVariable("adId") Integer adId,HttpSession session)  {
		boolean dm = adService.delAd(adId);
		return StateResult.getSR(dm);
	}
	/**
	 * 渠道广告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count =adService.countAll();
		return count;
	}
	/**
	 * 渠道广告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/{channelId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByChannelId(@PathVariable("channelId") Integer channelId,HttpSession session)  {
		int count =adService.countAllByChannelId(channelId);
		return count;
	}
	/**
	 * 渠道广告单个加载
	 * @return
	 */
	@RequestMapping(value = "/{adId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Ad loadAd(@PathVariable("adId") Integer adId,HttpSession session)  {
		Ad ad=new Ad();
		ad = adService.loadAd(adId);
		return ad;
	}
	/**
	 * 图片修改
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String updateAdImg(
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
	public @ResponseBody String addAdImg(
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
