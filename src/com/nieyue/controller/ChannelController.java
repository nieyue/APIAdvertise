package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Channel;
import com.nieyue.exception.StateResult;
import com.nieyue.service.ChannelService;
import com.nieyue.util.MyDESutil;
import com.nieyue.weixin.UnifiedOrderUtil;


/**
 * 渠道控制类
 * @author yy
 *
 */
@Controller("channelController")
@RequestMapping("/channel")
public class ChannelController {
	@Resource
	private ChannelService channelService;
	
	/**
	 * 渠道分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Channel> browsePagingChannel(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="channel_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Channel> list = new ArrayList<Channel>();
			list= channelService.browsePagingChannel(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 渠道全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Channel> browseAllChannel(
			@RequestParam(value="orderName",required=false,defaultValue="channel_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Channel> list = new ArrayList<Channel>();
		list= channelService.browseAllChannel( orderName, orderWay);
		return list;
	}
	/**
	 * 渠道修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateChannel(@ModelAttribute Channel channel,HttpSession session)  {
		boolean um = channelService.updateChannel(channel);
		return StateResult.getSR(um);
	}
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/update/password", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePasswordChannel(@RequestParam(value="password")String password,@RequestParam(value="newpassword")String newpassword,HttpSession session)  {
		boolean um=false;
		if(session.getAttribute("channel")==null){
			return StateResult.getSR(um);
		}
		Channel channel = channelService.loadChannel(((Channel) session.getAttribute("channel")).getChannelId());
			if(!channel.getPassword().equals(MyDESutil.getMD5(password))){
				return StateResult.getSR(um);
			}
			
			channel.setPassword(MyDESutil.getMD5(newpassword));
			um = channelService.updateChannel(channel);
		return StateResult.getSR(um);
	}
	
	/**
	 * 渠道增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addChannel(@ModelAttribute Channel channel, HttpSession session) {
		channel.setPassword( MyDESutil.getMD5(channel.getPassword()));
		channel.setApiToken(UnifiedOrderUtil.createNonceStr());
		boolean am = channelService.addChannel(channel);
		return StateResult.getSR(am);
	}
	/**
	 *渠道 删除
	 * @return
	 */
	@RequestMapping(value = "/{channelId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delChannel(@PathVariable("channelId") Integer channelId,HttpSession session)  {
		boolean dm = channelService.delChannel(channelId);
		return StateResult.getSR(dm);
	}
	/**
	 * 渠道浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = channelService.countAll();
		return count;
	}
	/**
	 * 渠道单个加载
	 * @return
	 */
	@RequestMapping(value = "/{channelId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Channel loadChannel(@PathVariable("channelId") Integer channelId,HttpSession session)  {
		Channel channel=new Channel();
		channel = channelService.loadChannel(channelId);
		return channel;
	}
	/**
	 *渠道登录
	 * @return
	 */
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object loginChannel(@RequestParam(value="channelName")String channelName,@RequestParam(value="password")String password,HttpServletRequest request,HttpServletResponse response)  {
		Channel channel =new Channel();
		String md5pwd = MyDESutil.getMD5(password);
		channel= channelService.loginChannel(channelName, md5pwd);
		if(channel!=null){
			 //生成一个token，保存用户登录状态
	        // tokenManager.createToken("XuDeOAChannel",Channel.getChannelId(),request,response);
			request.getSession().setAttribute("channel", channel);
		}
		return channel;
	}

	/**
	 *渠道登出
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void loginoutChannel(HttpServletRequest request,HttpServletResponse response)  {
		 //tokenManager.deleteToken("XuDeOAChannel",request,response);
		request.getSession().invalidate();
	}
	/**
	 *渠道状态
	 * @return
	 */
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody boolean isloginChannel(HttpServletRequest request,HttpServletResponse response)  {
		//if(tokenManager.checkToken("XuDeOAChannel", tokenManager.getToken("XuDeOAChannel", request), request,response)){
			if(request.getSession().getAttribute("channel")!=null){
			return true;
		}
		return false;
	}
	
}
