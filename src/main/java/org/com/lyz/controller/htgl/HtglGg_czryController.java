package org.com.lyz.controller.htgl;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.DateUtil;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员管理
 * @author： 鲁玉震
 * @time： 2018/6/26
 */
@Controller
@RequestMapping("htgl/czry")
public class HtglGg_czryController {

    private final static Logger logger = Logger.getLogger(HtglXt_gnbController.class);

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    @RequestMapping()
    public String Index(HttpServletRequest request, Model model) throws SQLException{

        return "htgl/gg_czry/htglIndex_Czry";
    }

    /**
     * 获取数据列表
     * @param request 请求参数
     * @param splitPageInfo 分页信息
     * @return 返回信息
     * @throws SQLException 异常信息
     */
    @RequestMapping("/getAllCzry")
    @ResponseBody
    public Map<String,Object> getAllCzry(HttpServletRequest request, SplitPageInfo splitPageInfo,GG_CZRY gg_czry) throws SQLException{
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> allCzryList = CzryService.getAllCzryLimit(gg_czry.getMc(),splitPageInfo);
        JSONArray jsonArray = JSONArray.fromObject(allCzryList);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("code", "0");
        returnMap.put("msg", "");
        returnMap.put("count",allCzryList.size());
        returnMap.put("data",jsonArray);
        return returnMap;
    }

    /**
     * 修改操作人员状态
     * @param request 请求信息
     * @param gg_czry 操作人员信息
     * @return 修改结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editGg_czry_zt")
    @ResponseBody
    public ReturnValue editGg_czry_zt(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException{
        if (gg_czry.getQx() != null && gg_czry.getQx() == Constant_htgl.GG_CZRY_QX_CJGLY) {
            return ReturnValue.newErrorInstance("超级管理员不能禁用！");
        }
        //修改状态
        if (gg_czry.getZt() == Constant_htgl.GG_CZRY_ZT_JY) {
            gg_czry.setZt(Constant_htgl.GG_CZRY_ZT_ZC);
        } else {
            gg_czry.setZt(Constant_htgl.GG_CZRY_ZT_JY);
        }
        CzryService.update(gg_czry);
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 删除操作人员
     * @param request 请求信息
     * @param gg_czry 操作人员信息
     * @return 删除结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/deleteGg_czry")
    @ResponseBody
    public ReturnValue deleteGg_czry(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException {
        if (gg_czry.getQx() != null && gg_czry.getQx() == Constant_htgl.GG_CZRY_QX_CJGLY) {
            return ReturnValue.newErrorInstance("超级管理员不能删除！");
        }
        CzryService.delete(gg_czry.getId());
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 跳转修改信息页面
     * @param request 请求信息
     * @param czryid 人员id
     * @param model 模型信息
     * @return 跳转页面
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editGg_czry")
    public String editGg_czry(HttpServletRequest request, String czryid, Model model) throws SQLException {
        GG_CZRY gg_czry = CzryService.selectById(czryid);
        List<Map<String, Object>> qxList = new ArrayList<Map<String, Object>>();
        Map<String, Object> qxMap;
        for (Map.Entry<Integer,String> entry:Constant_htgl.GG_CZRY_QXMap.entrySet()){
            qxMap = new HashMap<String, Object>();
            qxMap.put("key",entry.getKey());
            qxMap.put("value", entry.getValue());
            qxList.add(qxMap);
        }
        String csnyr = null;
        if (gg_czry != null) {
            csnyr = DateUtil.dateFormateStr(ConvertUtils.createString(gg_czry.getCsnyr()));
        }
        model.addAttribute("csnyr", csnyr);
        model.addAttribute("gg_czry", gg_czry);
        model.addAttribute("qxList", qxList);
        return "htgl/gg_czry/htglEdit_Czry";
    }

    /**
     * 保存修改信息
     * @param request 请求信息
     * @param gg_czry 人员信息
     * @return 修改结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/saveGg_czry")
    @ResponseBody
    public ReturnValue saveGg_czry(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException {
        GG_CZRY czry = CzryService.selectById(gg_czry.getId());
        gg_czry.setMm(czry.getMm());
        CzryService.save(gg_czry);
        return ReturnValue.newSuccessInstance("保存成功！");
    }

    /**
     * 校验密码
     * @param request 请求信息
     * @param gg_czry 人员信息
     * @return 校验结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/jymm")
    @ResponseBody
    public ReturnValue getJymm(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException {
        GG_CZRY czry = CzryService.selectById(gg_czry.getId());
        if (gg_czry.getMm().equals(czry.getMm())) {
            return ReturnValue.newSuccessInstance("验证通过！");
        }
        return ReturnValue.newErrorInstance("密码不匹配！");
    }

    /**
     * 修改密码
     *
     * @param request 请求信息
     * @param gg_czry 人员信息
     * @return 修改结果
     */
    @RequestMapping("/updateMm")
    @ResponseBody
    public ReturnValue updateMm(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException {
        GG_CZRY czry = CzryService.selectById(gg_czry.getId());
        czry.setMm(gg_czry.getMm());
        CzryService.update(czry);
        return ReturnValue.newSuccessInstance("修改成功！请重新登录系统！");
    }
}
