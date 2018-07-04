package org.com.lyz.controller;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.ImgService;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.*;
import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 注册、登录、初始化
 */
@Controller
@RequestMapping("zcdl")
public class ZcdlController {

    private final static Logger logger = Logger.getLogger(ZcdlController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    @Autowired
    @Qualifier("imgService")
    private ImgService imgService;

    /**
     * 注册用户
     * @param model
     * @param gg_czry
     * @return
     */
    @RequestMapping(value = "/zcczry")
    @ResponseBody
    public ReturnValue zcczryAction(Model model, GG_CZRY gg_czry) throws SQLException{
        try {
            String mm = SHAEncryptionUtil.SHAEncryption(gg_czry.getMm());
            gg_czry.setMm(mm);
        } catch (Exception e) {
            logger.error("注册失败，失败原因："+e.getMessage());
            e.printStackTrace();
            return ReturnValue.newErrorInstance("注册失败！");
        }
        GG_CZRY czry = CzryService.getCzryByDlh(gg_czry.getDlh());
        if(czry != null){
            logger.info("注册失败！账号已被占用！");
            return ReturnValue.newErrorInstance("注册失败，该登录号已被注册！");
        }
        CzryService.insert(gg_czry);
        logger.info("注册用户成功------------");
        return ReturnValue.newSuccessInstance("注册成功！欢迎使用本系统^_^");
    }

    @RequestMapping("/getHypy")
    @ResponseBody
    public Map<String,Object> getHypy(HttpServletRequest request,String mc){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String dlh = Pinyin4jUtil.getHypy(mc);
        returnMap.put("dlh", dlh);
        return returnMap;
    }


    /**
     * 登陆校验
     * @param czry 用户信息
     * @param session session
     * @return 登陆信息
     * @throws SQLException 错误信息
     */
    @RequestMapping(value = "/dlxtjy")
    @ResponseBody
    public ReturnValue dlxtAction(GG_CZRY czry, HttpSession session) throws SQLException{

        GG_CZRY gg_czry = CzryService.getCzryByDlh(czry.getDlh());
        if (gg_czry == null) {
            return ReturnValue.newErrorInstance("账号不存在！");
        }

        if (!gg_czry.getZt().equals(Constant_htgl.GG_CZRY_ZT_ZC)) {
            return ReturnValue.newErrorInstance("该账号已被禁止登陆！请联系管理员!");
        }
        try {
            //已经经过前台加密
            if (!gg_czry.getMm().equals(czry.getMm())) {
                return ReturnValue.newErrorInstance("密码不正确！");
            } else {
                //登陆用户添加session
                session.setAttribute("user",gg_czry);
                XT_GNB xt_gnb = new XT_GNB();
                xt_gnb.setDyqx(gg_czry.getQx());
                xt_gnb.setZt(Constant_htgl.XT_GNB_ZT_ZC);
                List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(xt_gnb);
                session.setAttribute("xtgnList", xtgnList);
                logger.info("用户【"+gg_czry.getMc()+"】登陆系统");
                return ReturnValue.newSuccessInstance("登陆成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnValue.newErrorInstance("发生未知错误，请联系管理员解决！");
        }
    }

    /**
     * 跳转登陆系统
     * @return
     */
    @RequestMapping("/htglMainHome")
    public String htglMainHome(HttpServletRequest request,HttpSession session,Model model) throws SQLException{
        logger.info("========进入后台管理页面=======");
        GG_CZRY gg_czry = (GG_CZRY) session.getAttribute("user");
        XT_GNB xt_gnb = new XT_GNB();
        xt_gnb.setDyqx(gg_czry.getQx());
        xt_gnb.setZt(Constant_htgl.XT_GNB_ZT_ZC);
        List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(xt_gnb);
        String imgPath = "";
        if(StringUtils.isNotEmpty(gg_czry.getTxdz())){
            GG_IMGS gg_imgs  = imgService.selectById(gg_czry.getTxdz());
            imgPath = FileUtils.getZxImgPath(request) + gg_imgs.getTpmc();
        }
        session.setAttribute("xtgnList", xtgnList);
        model.addAttribute("showImg", imgPath);
        model.addAttribute("GG_CZRY_QX_PTYH",Constant_htgl.GG_CZRY_QX_PTYH);
        return "htgl/htglMainHome";
    }


    /**
     * 退出系统
     * @param session
     * @return
     */
    @RequestMapping("/exit")
    public String extiAction(HttpSession session){
        try {
            GG_CZRY czry = (GG_CZRY)session.getAttribute("user");
            logger.info("用户["+czry.getMc()+"]登录号["+czry.getDlh()+"]退出登陆");
            session.removeAttribute("user");
            session.removeAttribute("xtgnList");
        } catch (Exception e){
//            throw new MisException("发生未知异常");
            return ControllerUtils.getStringRedirect("/login.jsp");
        }
        return ControllerUtils.getStringRedirect("/login.jsp");
    }




}
