package org.com.lyz.controller.ltyl;

import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.constant.Constant_ltyl;
import org.com.lyz.controller.BaseController;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.ltyl.LtgnService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/9/28
 */
@Controller
@RequestMapping("/ltyl/jstx")
public class LtylJstxController extends BaseController {

    @Autowired
    private LtgnService ltgnService;

    @Autowired
    private CzryService czryService;

    /**
     * 即时通信页面
     * @param request 请求信息
     * @param fjid 房间id
     * @param model 模型信息
     * @return 跳转地址
     * @throws SQLException 异常信息
     */
    @RequestMapping("/jstxIndex")
    public String jstxIndex(HttpServletRequest request, String fjid, Model model) throws SQLException{
        GG_LTFJ gg_ltfj = ltgnService.getGg_lifj(fjid);
        //查询当前操作人是否在房间中。如果不在，添加，如果在，不添加
        GG_CZRY gg_czry = this.getGg_czry(request);
        GG_FJRYB fjryb = new GG_FJRYB();
        fjryb.setRyid(gg_czry.getId());
        fjryb.setFjid(gg_ltfj.getId());
        List<Map<String, Object>> ryIsInFj = ltgnService.getFjry(fjryb);
        if (ryIsInFj !=null && ryIsInFj.size() > 0){
        } else {
            fjryb.setFjid(gg_ltfj.getId());
            fjryb.setZt(Constant_ltyl.GG_FJRYB_ZT_ZC);
            ltgnService.saveGg_fjryb(fjryb);
        }
        //查询房间所有人员
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setFjid(gg_ltfj.getId());
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        for (Map<String,Object> map: fjryList) {
            GG_CZRY czry = czryService.selectById(ConvertUtils.createString(map.get("ryid")));
            map.put("rymc", czry.getMc());
        }
        model.addAttribute("ltfj", gg_ltfj);
        model.addAttribute("fjryList", fjryList);
        model.addAttribute("GG_FJRYB_ZT_ZC", Constant_ltyl.GG_FJRYB_ZT_ZC);
        return "ltyl/jstx/jstxIndex";
    }

    /**
     * 解散房间
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 操作结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/onJsfj")
    @ResponseBody
    public ReturnValue onJsfj(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        ltgnService.deleteJsfj(gg_ltfj);
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 退出房间
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 操作结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/onTcfj")
    @ResponseBody
    public ReturnValue onTcfj(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        GG_CZRY gg_czry = this.getGg_czry(request);
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setRyid(gg_czry.getId());
        gg_fjryb.setFjid(gg_ltfj.getId());
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        for (Map<String, Object> map: fjryList) {
            ltgnService.deleteGg_fjryb(ConvertUtils.createString(map.get("id")));
        }
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 禁言
     * @param request 请求信息
     * @param gg_fjryb 房间人员信息
     * @return 操作结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/onYhjy")
    @ResponseBody
    public Map<String, Object> onYhjy(HttpServletRequest request,GG_FJRYB gg_fjryb) throws SQLException{
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList == null || fjryList.size() <= 0){
            returnMap.put("state", ReturnValue.newErrorInstance());
            return returnMap;
        }
        GG_FJRYB fjryb = ltgnService.getGg_fjryb(ConvertUtils.createString(fjryList.get(0).get("id")));
        String showName = "禁言";
        if (fjryb.getZt() == Constant_ltyl.GG_FJRYB_ZT_ZC){
            fjryb.setZt(Constant_ltyl.GG_FJRYB_ZT_JY);
            showName = "解禁";
        } else if (fjryb.getZt() == Constant_ltyl.GG_FJRYB_ZT_JY){
            fjryb.setZt(Constant_ltyl.GG_FJRYB_ZT_ZC);
        } else {
            fjryb.setZt(Constant_ltyl.GG_FJRYB_ZT_ZC);
        }
        ltgnService.updateGg_fjryb(fjryb);
        returnMap.put("showName", showName);
        returnMap.put("state", ReturnValue.newSuccessInstance());
        return returnMap;
    }

    /**
     * 踢出房间
     * @param request 请求信息
     * @param gg_fjryb 房间人员信息
     * @return 操作结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/onYhtc")
    @ResponseBody
    public ReturnValue onYhtc(HttpServletRequest request,GG_FJRYB gg_fjryb) throws SQLException{
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList == null || fjryList.size() <= 0){
            return ReturnValue.newErrorInstance();
        }
        GG_LTFJ gg_ltfj = ltgnService.getGg_lifj(ConvertUtils.createString(fjryList.get(0).get("fjid")));
        if (gg_ltfj.getCjr().equals(ConvertUtils.createString(fjryList.get(0).get("ryid")))){
            return ReturnValue.newErrorInstance("房主不能踢出房间。");
        }
        ltgnService.deleteGg_fjryb(ConvertUtils.createString(fjryList.get(0).get("id")));
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 跳转即时通信页面
     * @param request 请求信息
     * @param fjid 房间id
     * @param model 模型信息
     * @return 跳转页面
     * @throws SQLException 异常信息
     */
    @RequestMapping("/jstxWebSocket")
    public String jstxWebSocket(HttpServletRequest request,String fjid,Model model) throws SQLException{
        GG_LTFJ gg_ltfj = ltgnService.getGg_lifj(fjid);
        GG_CZRY gg_czry = this.getGg_czry(request);
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setFjid(fjid);
        gg_fjryb.setRyid(gg_czry.getId());
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList != null && fjryList.size() > 0){
            gg_fjryb = ltgnService.getGg_fjryb(ConvertUtils.createString(fjryList.get(0).get("id")));
        }
        model.addAttribute("fjry", gg_fjryb);
        model.addAttribute("ltfj", gg_ltfj);
        model.addAttribute("GG_FJRYB_ZT_ZC", Constant_ltyl.GG_FJRYB_ZT_ZC);
        return "ltyl/jstx/jstxWebSocket";
    }

    /**
     * 发送信息前校验是否已经被禁言
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 操作结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/onJsxtSfjy")
    @ResponseBody
    public Map<String, Object> onJsxtSfjy(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        Map<String, Object> returnMap = new HashMap();
        GG_CZRY gg_czry = this.getGg_czry(request);
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setFjid(gg_ltfj.getId());
        gg_fjryb.setRyid(gg_czry.getId());
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList != null && fjryList.size() > 0){
            returnMap.put("state", fjryList.get(0).get("zt"));
        } else {
            returnMap.put("state","ytc");
        }
        return returnMap;
    }
}
