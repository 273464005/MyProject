package org.com.lyz.controller.htglcontroller;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htglservice.CzryService;
import org.com.lyz.service.htglservice.XtgnService;
import org.com.lyz.util.ControllerUtils;
import org.com.lyz.util.MisException;
import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(value = "zcczry")
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
        if(CzryService.insert(gg_czry)){
            logger.info("注册用户成功------------");
        }
        return ReturnValue.newSuccessInstance("注册成功！欢迎使用本系统~~");
    }


    /**
     * 登陆系统
     * @param czry 用户信息
     * @param session session
     * @return 登陆信息
     * @throws SQLException 错误信息
     */
    @RequestMapping(value = "dlxt")
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
            String mm = SHAEncryptionUtil.SHAEncryption(czry.getMm());
            if (!gg_czry.getMm().equals(mm)) {
                return ReturnValue.newErrorInstance("密码不正确！");
            } else {
                //登陆用户添加session
                session.setAttribute("user",gg_czry);
                List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(gg_czry.getQx());
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
     * 退出系统
     * @param session
     * @return
     */
    @RequestMapping("/exit")
    public String extiAction(HttpSession session){
        GG_CZRY czry = (GG_CZRY)session.getAttribute("user");
        logger.info("用户["+czry.getMc()+"]登录号["+czry.getDlh()+"]退出登陆");
        try {
            session.removeAttribute("user");
            session.removeAttribute("xtgnList");
        } catch (MisException e){
            throw new MisException("发生未知异常");
        }
        return ControllerUtils.getStringRedirect("/login.jsp");
    }


    /**
     * angularJS提交方式
     * --@RequestBody 处理angularJS传过来的数据，Content-Type为 application/json
     *  jquery请求的"Content-Type"默认为" application/x-www-form-urlencoded"，
     * @param request
     * @param gg_czry
     * @return
     */
    @RequestMapping("/angular/csaction")
    @ResponseBody
    public Map<String,Object> csAction(HttpServletRequest request, @RequestBody GG_CZRY gg_czry){
        Map<String, Object> returnMap = new HashMap<String,Object>();
        returnMap.put("dlh", gg_czry.getDlh());
        returnMap.put("mm", gg_czry.getMm());
        return returnMap;
    }

}
