package org.com.lyz.controller.htgl;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
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

    @RequestMapping("/deleteGg_czry")
    @ResponseBody
    public ReturnValue deleteGg_czry(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException {
        if (gg_czry.getQx() != null && gg_czry.getQx() == Constant_htgl.GG_CZRY_QX_CJGLY) {
            return ReturnValue.newErrorInstance("超级管理员不能删除！");
        }
        CzryService.delete(gg_czry.getId());
        return ReturnValue.newSuccessInstance();
    }
}
