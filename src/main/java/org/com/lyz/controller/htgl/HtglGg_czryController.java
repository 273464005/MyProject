package org.com.lyz.controller.htgl;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.base.model.po.IMG_LOG;
import org.com.lyz.constant.Constants_core;
import org.com.lyz.constant.Constants_htgl;
import org.com.lyz.controller.BaseController;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.ImgLogService;
import org.com.lyz.service.htgl.ImgService;
import org.com.lyz.util.*;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * 人员管理
 * @author： 鲁玉震
 * @time： 2018/6/26
 */
@Controller
@RequestMapping("htgl/czry")
public class HtglGg_czryController extends BaseController {

    private final static Logger logger = Logger.getLogger(HtglXt_gnbController.class);

    @Autowired
    @Qualifier("czryService")
    private CzryService CzryService;

    @Autowired
    @Qualifier("imgService")
    private ImgService imgService;

    @Autowired
    @Qualifier("imgLogService")
    private ImgLogService imgLogService;

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

        List<Map<String,Object>> allCzryList = CzryService.getAllCzryLimit(gg_czry,splitPageInfo);
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
        if (gg_czry.getQx() != null && gg_czry.getQx() == Constants_htgl.GG_CZRY_QX_CJGLY) {
            return ReturnValue.newErrorInstance("超级管理员不能禁用！");
        }
        //修改状态
        if (gg_czry.getZt() == Constants_htgl.GG_CZRY_ZT_JY) {
            gg_czry.setZt(Constants_htgl.GG_CZRY_ZT_ZC);
        } else {
            gg_czry.setZt(Constants_htgl.GG_CZRY_ZT_JY);
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
        if (gg_czry.getQx() != null && gg_czry.getQx() == Constants_htgl.GG_CZRY_QX_CJGLY) {
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
        //编辑的账户
        GG_CZRY gg_czry = CzryService.selectById(czryid);
        //当前登录账号
        GG_CZRY user = this.getGg_czry(request);
        List<Map<String, Object>> qxList = new ArrayList<Map<String, Object>>();
        Map<String, Object> qxMap;
        for (Map.Entry<Integer,String> entry: Constants_htgl.GG_CZRY_QXMap.entrySet()){
            //可以给账户添加和自己同级别的权限。
            if (user.getQx() <= entry.getKey()){
                qxMap = new HashMap<String, Object>();
                qxMap.put("key",entry.getKey());
                qxMap.put("value", entry.getValue());
                qxList.add(qxMap);
            }
        }
        String csnyr = null;
        if (gg_czry != null) {
            csnyr = DateUtil.dateFormateStr(ConvertUtils.createString(gg_czry.getCsnyr()));
        }
        GG_IMGS gg_imgs;
        if(StringUtils.isNotEmpty(gg_czry.getTxdz())){
            gg_imgs = imgService.selectById(gg_czry.getTxdz());
        } else {
            gg_imgs = new GG_IMGS();
        }
        //图片展示路径+图片名称，展示图片
        String imgPath = "";
        //展示图片宽度
        int height = 0;
        int width = Constants_htgl.GG_IMGS_WIDTH_SHOW;
        if(gg_imgs != null && StringUtils.isNotEmpty(gg_imgs.getPkValue())){
            //imgPath = FileUtils.getZxImgPath(request)+gg_imgs.getTpmc();
            imgPath = FileUtils.getZxImgPath(request)+gg_imgs.getId();
            height = ImgUtils.getImgShowHeight(gg_imgs);
        }
        model.addAttribute("gg_imgs", gg_imgs);
        model.addAttribute("showImg",imgPath);
        model.addAttribute("showImgWidth", width);
        model.addAttribute("showImgHeight", height);
        model.addAttribute("csnyr", csnyr);
        model.addAttribute("gg_czry", gg_czry);
        model.addAttribute("qxList", qxList);
        model.addAttribute("GG_CZRY_QX_PTYH", Constants_htgl.GG_CZRY_QX_PTYH);
        model.addAttribute("GG_CZRY_QX_CJGLY", Constants_htgl.GG_CZRY_QX_CJGLY);
        return "htgl/gg_czry/htglEdit_Czry";
    }

    /**
     * 获取大图
     * @param request 请求信息
     * @param gg_imgs 图片信息
     * @return 获取结果
     * @throws SQLException 异常信息
     */
    @RequestMapping("/getBigImg")
    @ResponseBody
    public Map<String, Object> getBigImg(HttpServletRequest request, GG_IMGS gg_imgs) throws SQLException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        GG_IMGS imgs = imgService.selectById(gg_imgs.getId());

        Map<String, Object> albumMap = LayerUtils.getBigImgMap(request, imgs);
        JSONArray jsonArray2 = JSONArray.fromObject(albumMap);
        int width = Constants_htgl.GG_IMGS_WIDTH_ALBUM;
        int height = ImgUtils.getImgShowHeight(imgs,width);
        returnMap.put("json", jsonArray2);
        returnMap.put("width", width);
        returnMap.put("height", height);
        return returnMap;
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

    /**
     * 上传图片
     * @param request 请求信息
     * @param file 文件信息
     * @return 上传结果
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map<String,Object> saveUploadImg(HttpServletRequest request,@RequestParam("file") MultipartFile file,String czryid) throws Exception{
        Map<String, Object> returnMap = new HashMap<String,Object>();

        String myFileName = file.getOriginalFilename();// 文件原名称
        String fileName = FileUtils.getFileRandomName(myFileName);
        //获取文件夹路径
        String path = FileUtils.getPath(czryid);
        File localFile = FileUtils.upload(path, fileName);

        file.transferTo(localFile);

        //图片数据
        GG_IMGS gg_imgs = new GG_IMGS();
        gg_imgs.setScsj(DateUtil.getLongCurrDateTime14());
        gg_imgs.setTpdz(path);
        gg_imgs.setTpmc(fileName);
        gg_imgs.setHeight(ImgUtils.getImgHeight(localFile));
        gg_imgs.setWidth(ImgUtils.getImgWidth(localFile));
        imgService.save(gg_imgs);

        //图片日志
        IMG_LOG img_log = new IMG_LOG();
        img_log.setLogsj(DateUtil.getLongCurrDateTime14());
        img_log.setImgid(gg_imgs.getId());
        img_log.setGlid(czryid);
        imgLogService.save(img_log);

        //人员头像修改
        GG_CZRY gg_czry = CzryService.selectById(czryid);
        gg_czry.setTxdz(gg_imgs.getId());
        CzryService.update(gg_czry);

        JSONArray jsonArray = JSONArray.fromObject(gg_imgs);
        returnMap.put("code", 0);
        returnMap.put("msg", "");
        returnMap.put("data", jsonArray);
        return returnMap;
    }

    /**
     * 查询用户的历史头像
     *
     * @param request 请求信息
     * @param ryid    人员id
     * @return 展示页面
     */
    @RequestMapping("/getHistoryImgs")
    public String getHistoryImgs(HttpServletRequest request, String ryid, Model model) throws SQLException{
        GG_CZRY gg_czry = CzryService.selectById(ryid);
        IMG_LOG img_log = new IMG_LOG();
        img_log.setGlid(gg_czry.getId());
        List<Map<String,Object>> historyImgList = imgLogService.getImgLogList(img_log);
        String showPath = FileUtils.getZxImgPath(request);
        List<Map<String,Object>> showImgPathList = new ArrayList<Map<String,Object>>();
        String showImgPath = null;
        for (Map<String,Object> historyMap : historyImgList) {
            GG_IMGS imgs = imgService.selectById(ConvertUtils.createString(historyMap.get("imgid")));
            if (imgs != null){
                showImgPath = showPath + imgs.getId();
                historyMap.put("showImgPath", showImgPath);
                historyMap.put("tpmc", imgs.getTpmc());
            }
            //当前头像设置为第一个展示
            if (gg_czry.getTxdz().equals(ConvertUtils.createString(historyMap.get("imgid")))){
                showImgPathList.add(0,historyMap);
            } else {
                showImgPathList.add(historyMap);
            }
        }
        model.addAttribute("czry", gg_czry);
        model.addAttribute("showImgPathList", showImgPathList);
        return "htgl/gg_czry/htglHistoryImgs";
    }

    /**
     * 更换头像
     * @param request 请求信息
     * @param ryid 人员id
     * @param tpid 图片id
     * @return 更换结果
     * @throws SQLException 异常信息
     */
    @ResponseBody
    @RequestMapping("/updateRyTx")
    public ReturnValue updateRyTx(HttpServletRequest request,String ryid,String tpid) throws SQLException{
        GG_CZRY gg_czry = CzryService.selectById(ryid);
        gg_czry.setTxdz(tpid);
        CzryService.update(gg_czry);
        return ReturnValue.newSuccessInstance("更换成功");
    }

    /**
     * 删除历史头像
     * @param request 请求信息
     * @param img_log 历史图片信息
     * @return 删除结果
     * @throws SQLException 异常信息
     */
    @ResponseBody
    @RequestMapping("/deleteImgLog")
    public ReturnValue deleteImgLog(HttpServletRequest request,IMG_LOG img_log) throws SQLException{
        IMG_LOG imgLog = imgLogService.select(img_log);
        GG_IMGS gg_imgs = imgService.selectById(imgLog.getImgid());
        try {
            FileUtils.delFile(gg_imgs.getTpmc());
            imgService.delete(gg_imgs);
            imgLogService.delete(img_log);
        } catch (IOException e) {
            return ReturnValue.newErrorInstance();
        }
        return ReturnValue.newSuccessInstance("删除成功！");
    }
}
