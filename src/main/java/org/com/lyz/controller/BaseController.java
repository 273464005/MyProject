package org.com.lyz.controller;

import org.com.lyz.base.model.po.GG_CZRY;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
}
