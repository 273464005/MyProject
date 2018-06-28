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
 */
@Service("czryService")
public class CzryServiceImpl implements CzryService {

    private final static Logger logger = Logger.getLogger(CzryServiceImpl.class);

    @Autowired
    private GG_CZRYDao czryDao;

    public boolean insert(GG_CZRY gg_czry) throws SQLException {
        gg_czry.setId(StringUtils.getUUID());
        gg_czry.setQx(Constant_htgl.GG_CZRY_QX_GLY);//暂时注册全部为普通管理员
        gg_czry.setZt(Constant_htgl.GG_CZRY_ZT_ZC);//新注册用户状态全部为正常
        logger.info("----添加成功----");
        return czryDao.insertSelective(gg_czry) > 0;
    }

    public boolean update(GG_CZRY gg_czry) throws SQLException {
        logger.info("----修改成功----");
        return czryDao.updateByPrimaryKeySelective(gg_czry) > 0;
    }

    public boolean delete(String id) throws SQLException {
        logger.info("----删除成功----");
        return czryDao.deleteByPrimaryKey(id) > 0;
    }

    public GG_CZRY getCzryByDlhMm(String dlh,String mm) throws SQLException {
        return czryDao.selectByDlhMm(dlh,mm);
    }

    public GG_CZRY getCzryByDlh(String dlh) throws SQLException {
        return czryDao.selectByDlh(dlh);
    }

    public List<Map<String,Object>> getAllCzryLimit(String mc,SplitPageInfo splitPageInfo) throws SQLException {
        if(mc != null && !"".equals(mc)){
            mc = "%" + mc + "%";
        }
        List<Map<String, Object>> returnList = czryDao.selectAllLimit(mc, splitPageInfo.getPage(), splitPageInfo.getLimit());
        for (Map<String, Object> map : returnList) {
            map.put("xbmc", Constant_htgl.getGG_CZRY_XBMap_Label(ConvertUtils.toInt(map.get("xb"))));
            map.put("ztmc", Constant_htgl.getGG_CZRY_ZTMap_Label(ConvertUtils.toInt(map.get("zt"))));
            map.put("qxmc", Constant_htgl.getGG_CZRY_QXMap_Label(ConvertUtils.toInt(map.get("qx"))));
        }
        return returnList;
    }
}
