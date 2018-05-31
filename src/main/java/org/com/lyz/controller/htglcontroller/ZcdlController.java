package org.com.lyz.controller.htglcontroller;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htglservice.CzryService;
import org.com.lyz.service.htglservice.XtgnService;
import org.com.lyz.util.ControllerUtils;
import org.com.lyz.util.MisException;
import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 注册、登录、初始化
 */
@Controller
@RequestMapping("/zcdl")
public class ZcdlController {

    private final static Logger logger = Logger.getLogger(ZcdlController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    /**
     * 注册用户
     * @param model
     * @param gg_czry
     * @return
     */
    @RequestMapping(value = "/htgl.zcczry.action")
    public String zcczryAction(Model model, GG_CZRY gg_czry) {
        try {
            String mm = SHAEncryptionUtil.SHAEncryption(gg_czry.getMm());
            gg_czry.setMm(mm);
        } catch (Exception e) {
            logger.error("加密失败！");
            model.addAttribute("msg", "注册失败，发生未知错误，请联系管理员解决！");
            e.printStackTrace();
            return "reg";
        }
        if(CzryService.insert(gg_czry)){
            logger.info("注册用户成功------------");
            model.addAttribute("msg", "注册成功");
        }
        return "reg";
    }

    /**
     * 登录系统
     * @return
     */
    @RequestMapping(value = "/htgl.dlxt.action")
    public String dlxtAction(Model model, GG_CZRY czry, HttpSession session, RedirectAttributes attr){
        GG_CZRY gg_czry = CzryService.getCzryByDlh(czry.getDlh());
        if (gg_czry == null) {
            model.addAttribute("msg", "账号不存在！");
            attr.addFlashAttribute("msg", "账号不存在！");
            return "login";
        }

        if (!gg_czry.getZt().equals(Constant_htgl.GG_CZRY_ZT_ZC)) {
            model.addAttribute("msg", "该账号已被禁止登陆！请联系管理员");
            return "login";
        }
        try {
            String mm = SHAEncryptionUtil.SHAEncryption(czry.getMm());
            if (!gg_czry.getMm().equals(mm)) {
                model.addAttribute("msg", "密码不正确！");
                attr.addFlashAttribute("msg", "密码不正确！");
                return "login";
            } else {
                //登陆用户添加session
                session.setAttribute("user",gg_czry);
                model.addAttribute("user",gg_czry);
                attr.addFlashAttribute("user", gg_czry);
                List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(gg_czry.getQx());
                model.addAttribute("xtgnList", xtgnList);
                attr.addFlashAttribute("xtgnList", xtgnList);
                logger.info("用户【"+gg_czry.getMc()+"】登陆系统");
                return "htgl/htglMainHome";
            }

        } catch (Exception e) {
            model.addAttribute("msg", "发生未知错误，请联系管理员解决！");
            e.printStackTrace();
            return "login";
        }
    }

    /**
     * 退出系统
     * @param session
     * @return
     */
    @RequestMapping("/exit.action")
    public String extiAction(HttpSession session){
        GG_CZRY czry = (GG_CZRY)session.getAttribute("user");
        logger.info("用户["+czry.getMc()+"]登录号["+czry.getDlh()+"]退出登陆");
        try {
            session.removeAttribute("user");
        } catch (MisException e){
            throw new MisException("发生未知异常");
        }
        return ControllerUtils.getStringRedirect("login.jsp");
    }

}
