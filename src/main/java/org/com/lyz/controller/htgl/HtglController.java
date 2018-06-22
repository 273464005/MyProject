package org.com.lyz.controller.htgl;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.ControllerUtils;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.MisException;
import org.com.lyz.util.encryptionutil.SHAEncryptionUtil;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 * 后台管理页面
 */
@Controller
@RequestMapping("htgl")
public class HtglController {

    private final static Logger logger = Logger.getLogger(HtglController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    /**
     * 功能管理列表页面初始化
     * @param request 请求信息
     * @param model 模型信息
     * @return 返回地址
     */
    @RequestMapping("/xtgnIndex")
    public String xtgnIndex(HttpServletRequest request,Model model,HttpSession session) throws SQLException{
        GG_CZRY gg_czry = (GG_CZRY) session.getAttribute("user");
        List<Map<String, Object>> fatherGnbList = XtgnService.getFatherGnbList(gg_czry.getQx());
        StringBuffer treeDate = new StringBuffer();
        treeDate.append("{name:'所有功能',id:'sygn',children:[");
        int i = 0,j = 0;
        for (Map<String, Object> fatherMap : fatherGnbList){
            treeDate.append("{name:'").append(fatherMap.get("gnmc")).append("',id:'").append(fatherMap.get("id")).append("'");
            treeDate.append(",children:[");
            List<Map<String, Object>> childrenGnbList = XtgnService.getChildrenGbnList(ConvertUtils.createString(fatherMap.get("id")), gg_czry.getQx());
            for (Map<String, Object> childrenMap : childrenGnbList) {
                treeDate.append("{name:'").append(childrenMap.get("gnmc")).append("',id:'").append(childrenMap.get("id")).append("'}");
                if (j == 0 && childrenGnbList.size() > 1) {
                    treeDate.append(",");
                }
                j++;
            }
            treeDate.append("]}");
            if (i == 0 && fatherGnbList.size() > 1) {
                treeDate.append(",");
            }
            i++;
        }
        treeDate.append("]}");
        model.addAttribute("treeDate",treeDate);
        return "htgl/htglIndex";
    }

    @RequestMapping("/editXt_gnb")
    public String editXt_gnb(HttpServletRequest request,String gnid,Model model) throws SQLException{
        XT_GNB xt_gnb = XtgnService.getXt_gnbById(gnid);
        List<Map<String, Object>> xt_gnbList = XtgnService.getXtgnList(Constant_htgl.GG_CZRY_QX_CJGLY);
        List<Map<String, Object>> qxList = new ArrayList<Map<String, Object>>();

        for (Map.Entry<Integer,String> entry:Constant_htgl.GG_CZRY_QX.entrySet()){
            Map<String, Object> qxMap = new HashMap<String, Object>();
            qxMap.put("key",entry.getKey());
            qxMap.put("value", entry.getValue());
            qxList.add(qxMap);
        }
        model.addAttribute("xt_gnb",xt_gnb);
        model.addAttribute("xt_gnbList", xt_gnbList);
        model.addAttribute("qxList",qxList);
        return "htgl/htglEditXt_gnb";
    }

    @RequestMapping("/saveXt_gnb")
    @ResponseBody
    public ReturnValue saveXt_gnb(HttpServletRequest request,XT_GNB xt_gnb) throws SQLException{

        int zt ;
        String ztstr = ConvertUtils.createString(xt_gnb.getZt());
        if (!"".equals(ztstr) && ztstr != null){
            zt = ConvertUtils.createInteger(ztstr);
        } else{
            zt = Constant_htgl.XT_GNB_ZT_ZC;
        }
        xt_gnb.setZt(zt);
        String fid = xt_gnb.getFid();
        int sxh;
        if(xt_gnb.getId() != null && "".equals(xt_gnb.getId())){
            Map<String,Object> map;
            if(fid != null && !"".equals(fid)){
                map = XtgnService.getMaxSxhByFid(fid);
            } else {
                xt_gnb.setFid(null);
                map = XtgnService.getMaxSxh();
            }
            if(map.size() > 0){
                sxh = ConvertUtils.createInteger(map.get("maxsxh")) + 1;
            } else {
                sxh = 1;
            }
        } else {
            sxh = xt_gnb.getSxh();
        }

        xt_gnb.setSxh(ConvertUtils.createInteger(sxh));
        XtgnService.saveXt_gnb(xt_gnb);
        return ReturnValue.newSuccessInstance();
    }

}
