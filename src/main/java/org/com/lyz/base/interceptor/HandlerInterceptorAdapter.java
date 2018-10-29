package org.com.lyz.base.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author： 鲁玉震
 * @time： 2018/8/1
 */
@Component
public class HandlerInterceptorAdapter implements HandlerInterceptor {

    private String[] allowUrls;

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //静态资源不拦截
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        if (allowUrls != null && allowUrls.length > 0){
            for (String url : allowUrls) {
                if (requestUrl.contains(url)) {
                    return true;
                }
            }
        }
        //起始页面，注册页面，登陆页面放行
        if(request.getServletPath().startsWith("reg.jsp") ||request.getServletPath().startsWith("index.html")){
            return true;
        }

        //注册用户，登陆 放行
        if(request.getServletPath().startsWith("/zcdl/")){
            return true;
        }

        //登陆用户放行
        if (request.getSession().getAttribute("user") != null){
            return true;
        }

        //非法请求 即这些请求需要登录后才能访问
        //重定向到登录页面
        //session注销
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() +"/index.html");
//        response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() +"/index.html");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
