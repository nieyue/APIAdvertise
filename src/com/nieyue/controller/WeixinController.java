package com.nieyue.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Admin;
import com.nieyue.exception.StateResult;
import com.nieyue.util.ThirdParty;
import com.nieyue.weixin.business.WeiXinBusiness;
/**
 * 微信接口
 * @author yy
 *
 */
@Controller("weixinController")
@RequestMapping("/weixin")
public class WeixinController {
	
	/**
	 * 微信jssdk 接口
	 *
	 * @return
	 */
	@RequestMapping(value="/js/connection",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String connectionWeiXin(@RequestParam("url")String url,HttpSession session){
		String json = WeiXinBusiness.WXJSSDK(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_CEYIFA_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_CEYIFA_SECRET), url);
		//雅耀的公众号
		//String json = PastUtil.getParam(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_SECRET),url,nonce_str,time_stamp);
		//放肆约
		//String json = PastUtil.getParam(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_FANGSIYUE_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_FANGSIYUE_SECRET),url,nonce_str,time_stamp);
		return json;
		
	}
	/**
	 * test1
	 * @return
	 */
	@RequestMapping(value = "/test1", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String test1(HttpSession session)  {
		return "redirect:http://www.baidu.com";
	}
	/**
	 * test2
	 * @return
	 */
	@RequestMapping(value = "/test2", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String test2(HttpSession session)  {
		return "redirect:/weixin/test1";
	}
	/**
	 * test3
	 * @return
	 */
	@RequestMapping(value = "/test3", method = {RequestMethod.GET,RequestMethod.POST})
	public String test3(HttpSession session)  {
		return "redirect:http://www.baidu.com";
	}
	/**
	 * test4
	 * @return
	 */
	@RequestMapping(value = "/test4", method = {RequestMethod.GET,RequestMethod.POST})
	public String test4(HttpSession session)  {
		return "redirect:/weixin/test3";
	}
}
