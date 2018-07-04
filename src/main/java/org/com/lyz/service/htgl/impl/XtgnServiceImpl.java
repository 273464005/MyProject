package org.com.lyz.service.htgl.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.com.lyz.base.dao.XT_GNBDao;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.StringUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/10.
 * 系统功能service
 */
@Service("xtgnService")
public class XtgnServiceImpl implements XtgnService{

	private static final Logger logger = Logger.getLogger(XtgnServiceImpl.class);

    @Autowired
    private XT_GNBDao xtgnDao;

    /**
     * 根据权限获取功能列表
     * @param xt_gnb 功能信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getXtgnList(XT_GNB xt_gnb) throws SQLException {
        List<Map<String,Object>> xtgnList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> fatherList = xtgnDao.selectByPrimaryKeyIsNull(xt_gnb.getDyqx(),xt_gnb.getZt());
        logger.info("获取功能列表，共"+fatherList.size()+"条");
        for (Map<String, Object> fMap : fatherList) {
            List<Map<String, Object>> childrenList = xtgnDao.selectByFid(ConvertUtils.createString(fMap.get("id")),xt_gnb.getDyqx(),xt_gnb.getZt());
            logger.info("获取子功能列表，共"+childrenList.size()+"条");
            fMap.put("childrenList",childrenList);
            xtgnList.add(fMap);
        }
        return xtgnList;
    }

    /**
     * 根据ID获取功能信息
     * @param id 功能id
     * @return 功能信息
     * @throws SQLException 异常信息
     */
    public XT_GNB getXt_gnbById(String id) throws SQLException{
        return xtgnDao.selectByPrimaryKey(id);
    }

    /**
     * 根据fid获取最大sxh
     * @param fid fid
     * @return 最大顺序号
     * @throws SQLException 异常信息
     */
    public Map<String, Object> getMaxSxhByFid(String fid) throws SQLException {
        return xtgnDao.selectMaxSxhByFid(fid);
    }

    /**
     * 获取顶级功能的最大顺序号
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public Map<String, Object> getMaxSxh() throws SQLException {
        return xtgnDao.selectMaxSxhByFidIsNull();
    }

    /**
     * 保存
     * @param xt_gnb 功能信息
     * @throws SQLException 异常信息
     */
    public void saveXt_gnb(XT_GNB xt_gnb) throws SQLException {
        if( xt_gnb.getId() != null && !xt_gnb.getId().equals("") ){
            xtgnDao.updateByPrimaryKeySelective(xt_gnb);
        } else {
            xt_gnb.setId(StringUtils.getUUID());
            xtgnDao.insertSelective(xt_gnb);
        }
        logger.info("----保存功能信息成功----");
    }

    /**
     * 添加
     * @param xt_gnb 功能信息
     * @throws SQLException 异常信息
     */
    public void insertXt_gnb(XT_GNB xt_gnb) throws SQLException {
        xtgnDao.insertSelective(xt_gnb);
        logger.info("----添加功能信息成功----");
    }

    public void updateXt_gnb(XT_GNB xt_gnb) throws SQLException {
        xtgnDao.updateByPrimaryKeySelective(xt_gnb);
        logger.info("----修改功能信息成功----");
    }

    public void deleteXt_gnb(XT_GNB xt_gnb) throws SQLException {
        xtgnDao.deleteByPrimaryKey(xt_gnb.getId());
        logger.info("----删除功能信息成功----");
    }

    public void deleteXt_gnb(String gnid) throws SQLException {
        xtgnDao.deleteByPrimaryKey(gnid);
        logger.info("----删除功能信息成功----");
    }

    /**
     * 获取该状态下所有的顶级功能
     * @param xt_gnb 功能信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getFatherGnbList(XT_GNB xt_gnb) throws SQLException{
        return xtgnDao.selectByPrimaryKeyIsNull(xt_gnb.getDyqx(),xt_gnb.getZt());
    }

    /**
     * 获取所有的子功能
     * @param xt_gnb 功能信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getAllChildrenGnbList(XT_GNB xt_gnb) throws SQLException {
        return xtgnDao.selectByFidIsNotNull(xt_gnb.getDyqx(),xt_gnb.getZt());
    }

    /**
     * 查询当前功能下的所有子功能
     * @param xt_gnb 功能信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getChildrenGbnList(XT_GNB xt_gnb) throws SQLException{
        return xtgnDao.selectByFid(xt_gnb.getFid(),xt_gnb.getDyqx(),xt_gnb.getZt());
    }

    /**
     * 分页查询
     * @param xt_gnb 功能信息
     * @param splitPageInfo 分页信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getAllGnbLimit(GG_CZRY gg_czry, XT_GNB xt_gnb, SplitPageInfo splitPageInfo) throws SQLException {
        if (StringUtils.isNotEmpty(xt_gnb.getGnmc())){
            xt_gnb.setGnmc("%" + xt_gnb.getGnmc() + "%");
        }
        List<Map<String, Object>> returnList = xtgnDao.selectAllByLimit(gg_czry.getQx(), xt_gnb.getGnmc(), splitPageInfo.getPage(), splitPageInfo.getLimit());
        for (Map<String, Object> map : returnList) {
            map.put("qxmc", Constant_htgl.getGG_CZRY_QXMap_Label(ConvertUtils.createInteger(map.get("dyqx"))));
            map.put("ztmc", Constant_htgl.getXT_GNB_ZTMap_Label(ConvertUtils.createInteger(map.get("zt"))));
            map.put("gnlbmc", Constant_htgl.getXT_GNB_GNLB_Map_Label(ConvertUtils.createInteger(map.get("gnlb"))));
            String fid = ConvertUtils.createString(map.get("fid"));
            String fmc = "";
            if (StringUtils.isNotEmpty(fid)) {
                XT_GNB gnb = xtgnDao.selectByPrimaryKey(fid);
                fmc = gnb.getGnmc();
            }
            map.put("fmc", fmc);
        }
        return returnList;
    }
}
