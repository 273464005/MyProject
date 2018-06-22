package org.com.lyz.service.htgl.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.com.lyz.base.dao.XT_GNBDao;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.StringUtils;
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
     * @param dyqx 权限
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getXtgnList(Integer dyqx) throws SQLException {
        List<Map<String,Object>> xtgnList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> fatherList = xtgnDao.selectByPrimaryKeyIsNull(dyqx);
        logger.info("获取功能列表，共"+fatherList.size()+"条");
        for (Map<String, Object> fMap : fatherList) {
            List<Map<String, Object>> childrenList = xtgnDao.selectByFid(ConvertUtils.createString(fMap.get("id")),dyqx);
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
    }

    /**
     * 添加
     * @param xt_gnb 功能信息
     * @throws SQLException 异常信息
     */
    public void insertXt_gnb(XT_GNB xt_gnb) throws SQLException {
        xtgnDao.insertSelective(xt_gnb);
    }

    /**
     * 获取所有的顶级功能
     * @param dyqx 对应权限
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getFatherGnbList(Integer dyqx) throws SQLException{
        return xtgnDao.selectByPrimaryKeyIsNull(dyqx);
    }

    /**
     * 查询当前功能下的所有子功能
     * @param fid FID
     * @param dyqx 对应权限
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getChildrenGbnList(String fid,Integer dyqx) throws SQLException{
        return xtgnDao.selectByFid(fid,dyqx);
    }
}
