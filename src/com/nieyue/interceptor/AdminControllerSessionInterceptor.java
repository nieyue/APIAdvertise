package com.nieyue.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.exception.MySellerSessionException;

/**
 * 用户session控制拦截器
 * @author yy
 *
 */
public class AdminControllerSessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
		
		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        //自定义token
//        if(method.getName().equals("loginAdmin")||method.getName().equals("isloginAdmin")||method.getName().equals("tokenAdmin")){
//        	return true;
//        }else if (manager.checkToken("XuDeOAadmin", manager.getToken("XuDeOAadmin", request), request, response)) {
//        	//验证token成功
//            return true;
//        }
        if(method.getName().equals("loginAdmin")
        ||method.getName().equals("isloginAdmin")
        ||method.getName().equals("connectionWeiXin")
        ||method.getName().equals("isloginChannel")
        ||method.getName().equals("loginChannel")
        ||(method.getName().indexOf("countAll")>-1)
        ||(method.getName().indexOf("browse")>-1)
        ||(method.getName().indexOf("Click")>-1)
        ){
        	return true;
        }else if (request.getSession().getAttribute("admin")!=null) {
        	return true;
        }else if (request.getSession().getAttribute("channel")!=null) {
        	return true;
        }
        //如果验证token失败
       throw new MySellerSessionException();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
