package org.com.lyz.controller.ltyl;

import net.sf.json.JSONArray;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.constant.Constant_ltyl;
import org.com.lyz.service.htgl.LtgnService;
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
public class LtylGg_ltfjController {

    @Autowired
    private LtgnService ltgnService;

    @RequestMapping()
    public String getFjlbTable(){
        return "ltyl/ltylMainHome";
    }

    @RequestMapping("/getFjTab")
    @ResponseBody
    public Map<String,Object> getFjTab(HttpServletRequest request, SplitPageInfo splitPageInfo, GG_LTFJ ltfj) throws SQLException{
        GG_CZRY gg_czry = (GG_CZRY) request.getSession().getAttribute("user");
        List<Map<String,Object>> ltfjList = ltgnService.getLtfjList(splitPageInfo,ltfj,gg_czry);
        if (ltfjList != null && ltfjList.size() > 0){
            for (Map<String,Object> ltfjMap: ltfjList) {
                ltfjMap.put("cjsjgsh",DateUtil.long2StrDate(ConvertUtils.createBigInteger(ltfjMap.get("cjsj"))));
                ltfjMap.put("GG_CZRY_QX_GLY",Constant_htgl.GG_CZRY_QX_GLY);
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

    @RequestMapping("/getRyfj")
    @ResponseBody
    public ReturnValue getRyfj(HttpServletRequest request, GG_CZRY gg_czry) throws SQLException{
//        List<Map<String,Object>> ryfjList = ltgnService.getRyfj(gg_czry);
//        if (ryfjList!=null && ryfjList.size()>0){
//            return ReturnValue.newErrorInstance("您已有房间，不能创建新房间。");
//        }
        return ReturnValue.newSuccessInstance("正在创建新房间···");
    }

    @RequestMapping("/editXjfj")
    public String editXjfj(HttpServletRequest request,String ltfjid, Model model) throws SQLException{
        GG_LTFJ ltfj = ltgnService.getGg_lifj(ltfjid);
        if (ltfj != null && StringUtils.isNotEmpty(ltfj.getFjh())){
        } else {
            ltfj = new GG_LTFJ();
            ltfj.setFjh(this.getLtfjh());
        }
        model.addAttribute("ltfj",ltfj);
        return "ltyl/ltylEdit_xjfj";
    }

    @RequestMapping("/saveLtfj")
    @ResponseBody
    public ReturnValue saveLtfj(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        gg_ltfj.setFjzt(Constant_ltyl.GG_LTFJ_FJZT_ZC);
        GG_CZRY czry = (GG_CZRY) request.getSession().getAttribute("user");
        gg_ltfj.setCjr(czry.getId());
        gg_ltfj.setCjrmc(czry.getMc());
        if (StringUtils.isEmpty(gg_ltfj.getFjms())){
            gg_ltfj.setFjms(Constant_ltyl.GG_LTFJ_FJMS_DEFAULT);
        }
        ltgnService.saveGg_ltfj(gg_ltfj);
        GG_FJRYB gg_fjryb = new GG_FJRYB();
        gg_fjryb.setFjid(gg_ltfj.getId());
        gg_fjryb.setRyid(gg_ltfj.getCjr());
        List<Map<String,Object>> fjryList = ltgnService.getFjry(gg_fjryb);
        if (fjryList != null && fjryList.size() > 0){
        } else {
            ltgnService.saveGg_fjryb(gg_fjryb);
        }
        return ReturnValue.newSuccessInstance("创建成功！");
    }

    @RequestMapping("/editGg_ltfj_zt")
    @ResponseBody
    public ReturnValue editGg_ltfj_zt(HttpServletRequest request,GG_LTFJ gg_ltfj) throws SQLException{
        if (gg_ltfj.getFjzt() == Constant_ltyl.GG_LTFJ_FJZT_JY){
            gg_ltfj.setFjzt(Constant_ltyl.GG_LTFJ_FJZT_ZC);
        }else if (gg_ltfj.getFjzt() == Constant_ltyl.GG_LTFJ_FJZT_ZC){
            gg_ltfj.setFjzt(Constant_ltyl.GG_LTFJ_FJZT_JY);
        }
        ltgnService.updateGg_ltfj(gg_ltfj);
        return ReturnValue.newSuccessInstance();
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
