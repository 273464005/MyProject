package org.com.lyz.controller.ltyl;

import net.sf.json.JSONArray;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.constant.Constants_htgl;
import org.com.lyz.constant.Constants_ltyl;
import org.com.lyz.controller.BaseController;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.ltyl.LtgnService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.DateUtil;
import org.com.lyz.util.StringUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
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
 * @time： 2018/9/13
 */
@Controller
@RequestMapping("ltyl/fjgl")
public class LtylGg_ltfjController extends BaseController {

    @Autowired
    private LtgnService ltgnService;

    @Autowired
    private CzryService czryService;

    /**
     * 初始化页面
     * @return 跳转页面
     */
    @RequestMapping()
    public String getFjlbTable(){
        return "ltyl/ltylMainHome";
    }

    /**
     * 获取所有聊天房间
     * @param request 请求信息
     * @param splitPageInfo 分页信息
     * @param ltfj 房间信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/getFjTab")
    @ResponseBody
    public Map<String,Object> getFjTab(HttpServletRequest request, SplitPageInfo splitPageInfo, GG_LTFJ ltfj) throws SQLException{
        GG_CZRY gg_czry = this.getGg_czry(request);
        List<Map<String,Object>> ltfjList = ltgnService.getLtfjList(splitPageInfo,ltfj,gg_czry);
        if (ltfjList != null && ltfjList.size() > 0){
            for (Map<String,Object> ltfjMap: ltfjList) {
                ltfjMap.put("cjsjgsh",DateUtil.long2StrDate(ConvertUtils.createBigInteger(ltfjMap.get("cjsj"))));
                ltfjMap.put("GG_CZRY_QX_GLY", Constants_htgl.GG_CZRY_QX_GLY);
                ltfjMap.put("GG_CZRY_QX_CJGLY", Constants_htgl.GG_CZRY_QX_CJGLY);
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(ltfjList);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("code", "0");
        returnMap.put("msg", "");
        returnMap.put("count",ltfjList.size());
        returnMap.put("data",jsonArray);
        return returnMap;
    }

    /**
     * 查询是否可以创建房间
     * @param request 请求信息
     * @param gg_czry 操作人信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/getRyfj")
    @ResponseBody
    public ReturnValue getRyfj(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException{
        List<Map<String,Object>> ryfjList = ltgnService.getRyfj(gg_czry);
        String tsxx = "正在创建新房间···";
        if (ryfjList!=null && ryfjList.size()>0){
            tsxx = "您已有房间，不能创建新房间。<br/><span style='color:red'>提示：如果搜索不到，请联系管理员</span>";
            return ReturnValue.newErrorInstance(tsxx);
        }
        return ReturnValue.newSuccessInstance(tsxx);
    }

    /**
     * 编辑房间信息
     * @param request 请求信息
     * @param ltfjid 房间id
     * @param model 视图信息
     * @return 跳转地址
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editXjfj")
    public String editXjfj(HttpServletRequest request,String ltfjid, Model model) throws SQLException{
        GG_LTFJ ltfj = ltgnService.getGg_lifj(ltfjid);
        GG_CZRY gg_czry = this.getGg_czry(request);
        if (ltfj != null && StringUtils.isNotEmpty(ltfj.getFjh())){
        } else {
            ltfj = new GG_LTFJ();
            ltfj.setFjh(this.getLtfjh());
            ltfj.setFjmc(gg_czry.getMc()+"的房间");
        }
        model.addAttribute("ltfj",ltfj);
        return "ltyl/ltylEdit_xjfj";
    }

    /**
     * 保存编辑结果
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 保存结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/saveLtfj")
    @ResponseBody
    public ReturnValue saveLtfj(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        gg_ltfj.setFjzt(Constants_ltyl.GG_LTFJ_FJZT_ZC);
        GG_CZRY czry = this.getGg_czry(request);
        gg_ltfj.setCjr(czry.getId());
        gg_ltfj.setCjrmc(czry.getMc());
        if (StringUtils.isEmpty(gg_ltfj.getFjms())){
            gg_ltfj.setFjms(Constants_ltyl.GG_LTFJ_FJMS_DEFAULT);
        }
        ltgnService.saveGg_ltfj(gg_ltfj);
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setFjid(gg_ltfj.getId());
        gg_fjryb.setRyid(gg_ltfj.getCjr());
        List<Map<String,Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList != null && fjryList.size() > 0){
        } else {
            gg_fjryb.setZt(Constants_ltyl.GG_FJRYB_ZT_ZC);
            ltgnService.saveGg_fjryb(gg_fjryb);
        }
        return ReturnValue.newSuccessInstance("操作成功！");
    }

    /**
     * 修改房间状态
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 修改结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editGg_ltfj_zt")
    @ResponseBody
    public ReturnValue editGg_ltfj_zt(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        if (gg_ltfj.getFjzt() == Constants_ltyl.GG_LTFJ_FJZT_JY){
            gg_ltfj.setFjzt(Constants_ltyl.GG_LTFJ_FJZT_ZC);
        }else if (gg_ltfj.getFjzt() == Constants_ltyl.GG_LTFJ_FJZT_ZC){
            gg_ltfj.setFjzt(Constants_ltyl.GG_LTFJ_FJZT_JY);
        }
        ltgnService.updateGg_ltfj(gg_ltfj);
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 删除房间
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 删除结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/deleteGg_ltfj")
    @ResponseBody
    public ReturnValue deleteGg_ltfj(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        GG_LTFJ ltfj = ltgnService.getGg_lifj(gg_ltfj.getId());
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setRyid(ltfj.getCjr());
        gg_fjryb.setFjid(ltfj.getId());
        List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList != null && fjryList.size() > 0){
            for (Map<String,Object> map:fjryList) {
                ltgnService.deleteGg_fjryb(ConvertUtils.createString(map.get("id")));
            }
        }
        ltgnService.deleteGg_ltfj(gg_ltfj);
        return ReturnValue.newSuccessInstance();
    }

    /**
     * 查询当前房间是否有密码
     * @param request 请求信息
     * @param fjid 房间id
     * @return 查询结果
     */
    @RequestMapping("/getFjmm")
    @ResponseBody
    public Map<String,Object> getFjmm(HttpServletRequest request,String fjid) throws SQLException{
        Map<String, Object> returnMap = new HashMap<String,Object>();
        GG_LTFJ gg_ltfj = ltgnService.getGg_lifj(fjid);
        if (gg_ltfj == null){
            returnMap.put("state",2);
            returnMap.put("text", "该房间已解散");
            return returnMap;
        }
        String state = "w";
        if (gg_ltfj != null && StringUtils.isNotEmpty(gg_ltfj.getFjmm())){
            state = "y";
        }
        //创建人不需要密码
        GG_CZRY gg_czry = this.getGg_czry(request);
        if (gg_ltfj != null && gg_czry.getId().equals(gg_ltfj.getCjr())) {
            state = "w";
        }
        returnMap.put("state", state);
        return returnMap;
    }

    /**
     * 验证密码是否匹配
     * @param request 请求信息
     * @param gg_ltfj 房间信息
     * @return 验证结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/jstxIndexJymm")
    @ResponseBody
    public ReturnValue jstxIndexJymm(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        GG_LTFJ ltfj = ltgnService.getGg_lifj(gg_ltfj.getId());
        System.out.println(" gg_ltfj.getFjmm()="+ gg_ltfj.getFjmm()+"/nltfj.getFjmm()="+ltfj.getFjmm());
        if (gg_ltfj != null && ltfj != null && gg_ltfj.getFjmm().equals(ltfj.getFjmm())){
            return ReturnValue.newSuccessInstance("校验通过，正在进入房间···");
        }
        return ReturnValue.newErrorInstance("密码不正确，请重试");
    }

    /**
     * 房间号组件规则
     * @return 房间号
     */
    private String getLtfjh(){
        String fjhBc = "0000000000000000";
        String fjh = ConvertUtils.createString(DateUtil.getNowTimeToOldTime());
        if (fjh.length() < 16){
            fjh = fjhBc.substring(0,16 - fjh.length()) + fjh;
        }
        return fjh;
    }
}
