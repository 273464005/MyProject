package org.com.lyz.controller.htgl;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constants_htgl;
import org.com.lyz.controller.BaseController;
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
public class HtglXt_gnbController extends BaseController {

    private final static Logger logger = Logger.getLogger(HtglXt_gnbController.class);

    @Autowired
    @Qualifier("xtgnService")
    private XtgnService XtgnService;

    @RequestMapping()
    public String index(HttpServletRequest request,Model model){

        return "htgl/xt_gnb/htglIndex_Gnb";
    }

    /**
     * 功能列表页面
     * @param request 请求信息
     * @param model 模型信息
     * @return 返回地址
     * @throws SQLException 异常信息
     */
    @RequestMapping("/xtgnIndexTable")
    public String xtgnIndexTable(HttpServletRequest request,Model model) throws SQLException{
        model.addAttribute("GG_CZRY_QX_PTYH", Constants_htgl.GG_CZRY_QX_PTYH);
        model.addAttribute("GG_CZRY_QX_GLY", Constants_htgl.GG_CZRY_QX_GLY);
        return "htgl/xt_gnb/htglTable_gnb";
    }

    /**
     * 功能管理列表(树形)页面初始化
     * @param request 请求信息
     * @param model 模型信息
     * @return 返回地址
     */
    @RequestMapping("/xtgnIndex")
    public String xtgnIndex(HttpServletRequest request,Model model,HttpSession session) throws SQLException{
        GG_CZRY gg_czry = this.getGg_czry(request);
        TreeNodeUtils treeNode = new TreeNodeUtils("ROOT","所有功能",true);
        //遍历状态
        for (Map.Entry<Integer, String> entry : Constants_htgl.XT_GNB_ZTMap.entrySet()) {
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
        model.addAttribute("zc", Constants_htgl.XT_GNB_ZT_ZC);
        model.addAttribute("jy", Constants_htgl.XT_GNB_ZT_JY);
        return "htgl/xt_gnb/htglTree_gnb";
    }

    /**
     * 初始化功能列表
     * @param request 请求信息
     * @param splitPageInfo 分页信息
     * @param gg_czry 人员信息
     * @param xt_gnb 功能信息
     * @return 初始化信息
     * @throws SQLException 异常信息
     */
    @RequestMapping("/getXt_gnb_table")
    @ResponseBody
    public Map<String,Object> getXt_gnb_table(HttpServletRequest request, SplitPageInfo splitPageInfo,GG_CZRY gg_czry,XT_GNB xt_gnb) throws SQLException{
        List<Map<String, Object>> tableGnbList = XtgnService.getAllGnbLimit(gg_czry,xt_gnb,splitPageInfo);
        JSONArray jsonArray = JSONArray.fromObject(tableGnbList);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("code", "0");
        returnMap.put("msg", "");
        returnMap.put("count",tableGnbList.size());
        returnMap.put("data",jsonArray);
        return returnMap;
    }

    /**
     * 跳转编辑功能页面
     * @param request 请求信息
     * @param session session信息
     * @param gnid 功能ID
     * @param model 模型信息
     * @return 返回地址
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editXt_gnb")
    public String editXt_gnb(HttpServletRequest request,HttpSession session,String gnid,Model model) throws SQLException{
        GG_CZRY gg_czry = this.getGg_czry(request);
        XT_GNB xt_gnb = XtgnService.getXt_gnbById(gnid);
        XT_GNB queryGnb = new XT_GNB();
        queryGnb.setDyqx(Constants_htgl.GG_CZRY_QX_CJGLY);
        queryGnb.setZt(Constants_htgl.XT_GNB_ZT_ZC);
        List<Map<String, Object>> xt_gnbList = XtgnService.getXtgnList(queryGnb);
        List<Map<String, Object>> qxList = new ArrayList<Map<String, Object>>();

        for (Map.Entry<Integer,String> entry: Constants_htgl.GG_CZRY_QXMap.entrySet()){
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
        model.addAttribute("gnjy", Constants_htgl.XT_GNB_ZT_JY);
        return "htgl/xt_gnb/htglEditXt_gnb";
    }

    /**
     * 保存功能
     * @param request 请求信息
     * @param xt_gnb 功能信息
     * @return 保存结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/saveXt_gnb")
    @ResponseBody
    public ReturnValue saveXt_gnb(HttpServletRequest request,XT_GNB xt_gnb) throws SQLException{

        //编辑保存信息
        int zt ;
        String ztstr = ConvertUtils.createString(xt_gnb.getZt());
        if (!"".equals(ztstr) && ztstr != null){
            zt = ConvertUtils.createInteger(ztstr);
        } else{
            zt = Constants_htgl.XT_GNB_ZT_ZC;
        }
        xt_gnb.setZt(zt);
        String fid = xt_gnb.getFid();
        int sxh;
        if(xt_gnb.getId() == null || "".equals(xt_gnb.getId())){
            Map<String,Object> map;
            if(fid != null && !"".equals(fid)){
                map = XtgnService.getMaxSxhByFid(fid);
            } else {
                xt_gnb.setFid(null);
                map = XtgnService.getMaxSxh();
            }
            if(map != null && map.size() > 0){
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
        //查询是否有子功能，如果有，全部修改
        XT_GNB childrenQueryGnb = new XT_GNB();
        childrenQueryGnb.setFid(xt_gnb.getId());
        List<Map<String, Object>> childrenList = XtgnService.getChildrenGbnList(childrenQueryGnb);
        if (childrenList != null && childrenList.size() > 0) {
            for (Map<String, Object> map : childrenList) {
                XT_GNB children = XtgnService.getXt_gnbById(ConvertUtils.createString(map.get("id")));
                children.setZt(xt_gnb.getZt());
                children.setDyqx(xt_gnb.getDyqx());
                XtgnService.updateXt_gnb(children);
            }
        }
        return ReturnValue.newSuccessInstance("保存成功！");
    }

    /**
     * 修改功能状态
     * @param request 请求信息
     * @param xt_gnb 功能信息
     * @return 修改结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/editXt_gnb_zt")
    @ResponseBody
    public ReturnValue editXt_gnb_zt(HttpServletRequest request,XT_GNB xt_gnb) throws SQLException{
        if(xt_gnb.getZt() == Constants_htgl.XT_GNB_ZT_JY){
            xt_gnb.setZt(Constants_htgl.XT_GNB_ZT_ZC);
        } else {
            xt_gnb.setZt(Constants_htgl.XT_GNB_ZT_JY);
        }
        XtgnService.updateXt_gnb(xt_gnb);
        return ReturnValue.newSuccessInstance("操作成功！");
    }

    /**
     * 删除功能信息
     * @param request 请求信息
     * @param xt_gnb 功能信息
     * @return 删除结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/deleteXt_gnb")
    @ResponseBody
    public ReturnValue deleteXt_gnb(HttpServletRequest request,XT_GNB xt_gnb) throws SQLException{
        XtgnService.deleteXt_gnb(xt_gnb);
        return ReturnValue.newSuccessInstance("删除成功！");
    }

}
