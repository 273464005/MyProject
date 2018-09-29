package org.com.lyz.service.htgl.impl;

import org.apache.log4j.Logger;
import org.com.lyz.base.dao.GG_CZRYDao;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.StringUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/17.
 * 人员services
 */
@Service("czryService")
public class CzryServiceImpl implements CzryService {

    private final static Logger logger = Logger.getLogger(CzryServiceImpl.class);

    @Autowired
    private GG_CZRYDao czryDao;

    /**
     * 添加
     * @param gg_czry 人员信息
     * @throws SQLException 异常信息
     */
    public void insert(GG_CZRY gg_czry) throws SQLException {
        gg_czry.setId(StringUtils.getUUID());
        gg_czry.setQx(Constant_htgl.GG_CZRY_QX_PTYH);//暂时注册全部为普通人员
        gg_czry.setZt(Constant_htgl.GG_CZRY_ZT_ZC);//新注册用户状态全部为正常
        logger.info("----添加操作人员成功----");
        czryDao.insertSelective(gg_czry);
    }

    /**
     * 修改
     * @param gg_czry 人员信息
     * @throws SQLException 异常信息
     */
    public void update(GG_CZRY gg_czry) throws SQLException {
        logger.info("----修改操作人员成功----");
        czryDao.updateByPrimaryKeySelective(gg_czry);
    }

    /**
     * 删除
     * @param id 人员id
     * @throws SQLException 异常信息
     */
    public void delete(String id) throws SQLException {
        logger.info("----删除操作人员成功----");
        czryDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存
     * @param gg_czry 人员信息
     * @throws SQLException 异常信息
     */
    public void save(GG_CZRY gg_czry) throws SQLException {
        if(gg_czry.getId() != null && !"".equals(gg_czry.getId())){
            czryDao.updateByPrimaryKeySelective(gg_czry);
        } else {
            czryDao.insertSelective(gg_czry);
        }
        logger.info("----保存操作人员成功----");
    }

    /**
     * 查询
     * @param id 人员id
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public GG_CZRY selectById(String id) throws SQLException {
        return czryDao.selectByPrimaryKey(id);
    }

    /**
     * 根据登录号密码查询
     * @param dlh 登录号
     * @param mm 密码
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public GG_CZRY getCzryByDlhMm(String dlh, String mm) throws SQLException {
        return czryDao.selectByDlhMm(dlh,mm);
    }

    /**
     * 根据登录号查询
     * @param dlh 登录号
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public GG_CZRY getCzryByDlh(String dlh) throws SQLException {
        return czryDao.selectByDlh(dlh);
    }

    /**
     * 分页查询
     * @param gg_czry 操作人员信息
     * @param splitPageInfo 分页信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String,Object>> getAllCzryLimit(GG_CZRY gg_czry,SplitPageInfo splitPageInfo) throws SQLException {
        if(StringUtils.isNotEmpty(gg_czry.getMc())){
            gg_czry.setMc("%" + gg_czry.getMc() + "%");
        }
        List<Map<String, Object>> returnList = czryDao.selectAllLimit(gg_czry.getMc(), gg_czry.getQx(), splitPageInfo.getPage(), splitPageInfo.getLimit());
        for (Map<String, Object> map : returnList) {
            map.put("sjh", StringUtils.idAndMobileNumToAsterisk(ConvertUtils.createString(map.get("sjh"))));
            map.put("sfzh", StringUtils.idAndMobileNumToAsterisk(ConvertUtils.createString(map.get("sfzh"))));
            map.put("xbmc", Constant_htgl.getGG_CZRY_XBMap_Label(ConvertUtils.toInt(map.get("xb"))));
            map.put("ztmc", Constant_htgl.getGG_CZRY_ZTMap_Label(ConvertUtils.toInt(map.get("zt"))));
            map.put("qxmc", Constant_htgl.getGG_CZRY_QXMap_Label(ConvertUtils.toInt(map.get("qx"))));
        }
        return returnList;
    }
}
