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
}
