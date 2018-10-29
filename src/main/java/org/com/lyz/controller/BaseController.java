package org.com.lyz.controller;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constants_core;
import org.com.lyz.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/9/28
 */
public class BaseController {

    /**
     * 从session中获取用户
     * @param request 请求信息
     * @return 结果
     */
    public GG_CZRY getGg_czry(HttpServletRequest request){
        return (GG_CZRY) request.getSession().getAttribute("user");
    }
    public GG_CZRY getGg_czry(HttpSession session){
        return (GG_CZRY) session.getAttribute("user");
    }

    /**
     * 在session中保存用户信息
     * @param request 请求信息
     * @param obj 需要保存的信息
     */
    public void setGg_czry(HttpServletRequest request,Object obj){
        request.getSession().setAttribute("user",obj);
    }
    public void setGg_czry(HttpSession session, Object obj){
        session.setAttribute("user",obj);
    }

    /**
     * session中添加路径
     * @param request 请求信息
     */
    public void setBasePath(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.getSession().setAttribute("path", path);
        request.getSession().setAttribute("basePath", basePath);
    }

    /**
     * 获取绝对路径
     * @param request 请求信息
     * @return 绝对路径
     */
    public String getBasePath(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("basePath");
    }

    /**
     * 获取路径
     * @param request 请求信息
     * @return 路径
     */
    public String getPath(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("path");
    }

    /**
     * 获取IP地址
     * @param request 请求信息
     * @return IP地址
     */
    public String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && "unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1){
                return ip.substring(0,index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 底部固定区域展示信息
     * @return
     */
    public String getFooter(){
        StringBuffer show = new StringBuffer();
        show.append("&nbsp;&nbsp;&nbsp;").append("<span class=\"layui-breadcrumb\" lay-separator=\"&nbsp;\" style=\"visibility: visible;\">");
        show.append("<a href=\"####").append("\">").append(Constants_core.FOOTER_SHOW_EMAIL).append("</a>");
        show.append("<a href=\"").append(Constants_core.FOOTER_SHOW_GITHUB).append("\" target=\"_blank\">").append("GitHub").append("</a>");
        show.append("</span>");
        return show.toString();
    }
}
