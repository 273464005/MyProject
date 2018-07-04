package org.com.lyz.controller.htgl;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.TreeNodeUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
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
@RequestMapping("htgl/xtgn")
public class HtglXt_gnbController {

    private final static Logger logger = Logger.getLogger(HtglXt_gnbController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;



    /**
     * 功能管理列表页面初始化
     * @param request 请求信息
     * @param model 模型信息
     * @return 返回地址
     */
    @RequestMapping("/xtgnIndex")
    public String xtgnIndex(HttpServletRequest request,Model model,HttpSession session) throws SQLException{
        GG_CZRY gg_czry = (GG_CZRY) session.getAttribute("user");
        TreeNodeUtils treeNode = new TreeNodeUtils("ROOT","所有功能",true);
        //遍历状态
        for (Map.Entry<Integer, String> entry : Constant_htgl.XT_GNB_ZTMap.entrySet()) {
            TreeNodeUtils oneTreeNode = new TreeNodeUtils();
            oneTreeNode.setId(ConvertUtils.createString(entry.getKey()));
            oneTreeNode.setName(entry.getValue());
            XT_GNB xt_gnb = new XT_GNB();
            xt_gnb.setZt(entry.getKey());
            xt_gnb.setDyqx(gg_czry.getQx());
            List<Map<String, Object>> fatherGnbList = XtgnService.getFatherGnbList(xt_gnb);
            List<Map<String, Object>> childrenGnbList = XtgnService.getAllChildrenGnbList(xt_gnb);

            //子功能暂存
            Map<String, List<TreeNodeUtils>> childrenCacheMap = new HashMap<String, List<TreeNodeUtils>>();
            //遍历fid不为null的功能
            for (Map<String, Object> childrenMap : childrenGnbList) {
                TreeNodeUtils childrenTreeNode = new TreeNodeUtils();
                childrenTreeNode.setId(ConvertUtils.createString(childrenMap.get("id")));
                childrenTreeNode.setName(ConvertUtils.createString(childrenMap.get("gnmc")));
                String childrenFid = ConvertUtils.createString(childrenMap.get("fid"));
                XT_GNB queryGnb = XtgnService.getXt_gnbById(childrenFid);
                if (queryGnb.getZt() == entry.getKey()) {
                    if(!childrenCacheMap.containsKey(childrenFid)){
                        List<TreeNodeUtils> treeNodeUtilsList = new ArrayList<TreeNodeUtils>();
                        treeNodeUtilsList.add(childrenTreeNode);
                        childrenCacheMap.put(childrenFid, treeNodeUtilsList);
                    } else {
                        List<TreeNodeUtils> treeNodeUtilsList = childrenCacheMap.get(childrenFid);
                        treeNodeUtilsList.add(childrenTreeNode);
                        childrenCacheMap.put(childrenFid, treeNodeUtilsList);
                    }
                } else {
                    oneTreeNode.addChildrenNode(childrenTreeNode);
                }

            }

            //遍历fid为null的功能
            for (Map<String, Object> fatherMap : fatherGnbList) {
                TreeNodeUtils fatherTreeNode = new TreeNodeUtils();
                fatherTreeNode.setId(ConvertUtils.createString(fatherMap.get("id")));
                fatherTreeNode.setName(ConvertUtils.createString(fatherMap.get("gnmc")));
                fatherTreeNode.setItems(childrenCacheMap.get(ConvertUtils.createString(fatherMap.get("id"))));
                oneTreeNode.addChildrenNode(fatherTreeNode);
            }
            treeNode.addChildrenNode(oneTreeNode);
        }
        model.addAttribute("treeDate", treeNode.toString());
        model.addAttribute("zc", Constant_htgl.XT_GNB_ZT_ZC);
        model.addAttribute("jy", Constant_htgl.XT_GNB_ZT_JY);
        return "htgl/xt_gnb/htglIndex_Gnb";
    }

    @RequestMapping("/getXt_gnb_table")
    @ResponseBody
    public Map<String,Object> getXt_gnb_table(HttpServletRequest request, SplitPageInfo splitPageInfo){

        return null;
    }

    @RequestMapping("/editXt_gnb")
    public String editXt_gnb(HttpServletRequest request,HttpSession session,String gnid,Model model) throws SQLException{
        GG_CZRY gg_czry = (GG_CZRY) session.getAttribute("user");
        XT_GNB xt_gnb = XtgnService.getXt_gnbById(gnid);
        XT_GNB queryGnb = new XT_GNB();
        queryGnb.setDyqx(Constant_htgl.GG_CZRY_QX_CJGLY);
        queryGnb.setZt(Constant_htgl.XT_GNB_ZT_ZC);
        List<Map<String, Object>> xt_gnbList = XtgnService.getXtgnList(queryGnb);
        List<Map<String, Object>> qxList = new ArrayList<Map<String, Object>>();

        for (Map.Entry<Integer,String> entry:Constant_htgl.GG_CZRY_QXMap.entrySet()){
            if(gg_czry.getQx() <= entry.getKey()){
                Map<String, Object> qxMap = new HashMap<String, Object>();
                qxMap.put("key",entry.getKey());
                qxMap.put("value", entry.getValue());
                qxList.add(qxMap);
            }
        }
        model.addAttribute("xt_gnb",xt_gnb);
        model.addAttribute("xt_gnbList", xt_gnbList);
        model.addAttribute("qxList",qxList);
        model.addAttribute("gnjy", Constant_htgl.XT_GNB_ZT_JY);
        return "htgl/xt_gnb/htglEditXt_gnb";
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
        if(xt_gnb.getId() == null && "".equals(xt_gnb.getId())){
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
            if(xt_gnb.getFid() != null && !"".equals(xt_gnb.getFid())){
            } else {
                xt_gnb.setFid(null);
            }
            sxh = xt_gnb.getSxh();
        }

        xt_gnb.setSxh(ConvertUtils.createInteger(sxh));
        XtgnService.saveXt_gnb(xt_gnb);
        return ReturnValue.newSuccessInstance("保存成功！");
    }

}
