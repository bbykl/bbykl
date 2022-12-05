package top.bbykl.crm.setting.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.bbykl.crm.commons.contants.contants;
import top.bbykl.crm.setting.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 盛祥进
 * @version 1.0
 * @description: 拦截器，拦截非登录状态下的请求
 * @date 2022/11/17 14:02
 */
public class loginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        执行方法前
        User user = (User) request.getSession().getAttribute(contants.USER_SESSION);
        if (user==null){
            //进行拦截,跳转到登录页面
            System.out.println("路径为："+request.getContextPath());
            response.sendRedirect("/"+request.getContextPath());
            return false;
        }
        else return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
