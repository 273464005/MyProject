package org.com.lyz.controller;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constants_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.ImgService;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.*;
import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
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
public class ZcdlController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(ZcdlController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    @Autowired
    @Qualifier("imgService")
    private ImgService imgService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        request.getSession().setAttribute("","");
        return "zcdl/login";
    }

    @RequestMapping("/reg")
    public String reg(HttpServletRequest request){
        return "zcdl/reg";
    }

    /**
     * 底部固定区域
     * @param request 请求信息
     * @return 返回结果
     */
    @ResponseBody
    @RequestMapping("/showFooter")
    public Map<String,Object> showFooter(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("showFooter",this.getFooter());
        return map;
    }
    /**
     * 注册用户
     * @param request 请求信息
     * @param gg_czry 人员信息
     * @param yzm 验证码
     * @return
     */
    @RequestMapping(value = "/zcczry")
    @ResponseBody
    public ReturnValue zcczryAction(HttpServletRequest request, GG_CZRY gg_czry, String yzm) throws SQLException{
        try {
            String mm = SHAEncryptionUtil.SHAEncryption(gg_czry.getMm());
            gg_czry.setMm(mm);
        } catch (Exception e) {
            logger.error("注册失败，失败原因："+e.getMessage());
            e.printStackTrace();
            return ReturnValue.newErrorInstance("注册失败！");
        }
        if (!validationCode(request,yzm)){
            return ReturnValue.newErrorInstance("验证码不匹配");
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

    /**
     * 将汉字转换为汉语拼音
     * @param request 请求信息
     * @param mc 需要转换的字
     * @return 转换结果
     */
    @RequestMapping("/getHypy")
    @ResponseBody
    public Map<String,Object> getHypy(HttpServletRequest request,String mc){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String dlh = Pinyin4jUtil.getHypy(mc);
        returnMap.put("dlh", dlh);
        return returnMap;
    }

    /**
     * 验证码
     * @param request 请求信息
     * @param response 请求信息
     * @throws Exception 异常信息
     */
    @ResponseBody
    @RequestMapping("/validationCode")
    public void getValidationCode(HttpServletRequest request , HttpServletResponse response) throws Exception{
        Object [] validationImge = ImgUtils.getValidationCode();
        request.getSession().setAttribute("validation_code",validationImge[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) validationImge[1];
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpeg", os);
        os.flush();
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
    public ReturnValue dlxtAction(HttpServletRequest request,GG_CZRY czry, String yzm ,HttpSession session) throws SQLException{

        if (!validationCode(request,yzm)){
            return ReturnValue.newErrorInstance("验证码不匹配");
        }
        GG_CZRY gg_czry = CzryService.getCzryByDlh(czry.getDlh());
        if (gg_czry == null) {
            return ReturnValue.newErrorInstance("账号不存在！");
        }

        if (!gg_czry.getZt().equals(Constants_htgl.GG_CZRY_ZT_ZC)) {
            return ReturnValue.newErrorInstance("该账号已被禁止登陆！请联系管理员!");
        }
        try {
            //已经经过前台加密
            if (!gg_czry.getMm().equals(czry.getMm())) {
                return ReturnValue.newErrorInstance("密码不正确！");
            } else {
                //登陆用户添加session
                this.setGg_czry(session,gg_czry);
                XT_GNB xt_gnb = new XT_GNB();
                xt_gnb.setDyqx(gg_czry.getQx());
                xt_gnb.setZt(Constants_htgl.XT_GNB_ZT_ZC);
                List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(xt_gnb);
                session.setAttribute("xtgnList", xtgnList);
                //session添加绝对路径
                this.setBasePath(request);
                logger.info("用户【"+gg_czry.getMc()+"】登陆系统，登录地址【" + getIp(request) + "】");
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
        try {
            logger.info("========初始化后台管理页面内容=======");
            GG_CZRY gg_czry = this.getGg_czry(request);
            gg_czry = CzryService.selectById(gg_czry.getId());
            this.setGg_czry(request,gg_czry);
            XT_GNB xt_gnb = new XT_GNB();
            xt_gnb.setDyqx(gg_czry.getQx());
            xt_gnb.setZt(Constants_htgl.XT_GNB_ZT_ZC);
            List<Map<String,Object>> xtgnList = XtgnService.getXtgnList(xt_gnb);
            String imgPath = "";
            if(StringUtils.isNotEmpty(gg_czry.getTxdz())){
//                GG_IMGS gg_imgs  = imgService.selectById(gg_czry.getTxdz());
//                imgPath = FileUtils.getZxImgPath(request) + gg_imgs.getTpmc();
                imgPath = FileUtils.getZxImgPath(request) + gg_czry.getTxdz();
            }
            session.setAttribute("xtgnList", xtgnList);
            request.getSession().setAttribute("sss","sss");
            this.setBasePath(request);
            model.addAttribute("showImg", imgPath);
            model.addAttribute("GG_CZRY_QX_PTYH",Constants_htgl.GG_CZRY_QX_PTYH);
            return "htgl/htglMainHome";
        } catch (Exception e){
            logger.info("========session失效，请重新登录=======");
            return ControllerUtils.getStringRedirect("/index.html");
        }
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
            return ControllerUtils.getStringRedirect("/index.html");
        }
        return ControllerUtils.getStringRedirect("/index.html");
    }

    /**
     * 验证码校验
     * @param request 请求信息
     * @param yzm 输入的验证码
     * @return 校验结果
     */
    private boolean validationCode(HttpServletRequest request,String yzm){
        HttpSession session = request.getSession();
        String validation_code = ConvertUtils.createString(session.getAttribute("validation_code"));
        if (yzm != null){
            String yzmUp = yzm.toUpperCase();
            if (!validation_code.equals(yzmUp)){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}
