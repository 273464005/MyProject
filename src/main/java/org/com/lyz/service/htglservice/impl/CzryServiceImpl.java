package org.com.lyz.service.htglservice.impl;

import org.apache.log4j.Logger;
import org.com.lyz.base.dao.GG_CZRYDao;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.service.htglservice.CzryService;
import org.com.lyz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
        return czryDao.insertSelective(gg_czry) > 0;
    }

    public boolean update(GG_CZRY gg_czry) throws SQLException {
        return czryDao.updateByPrimaryKeySelective(gg_czry) > 0;
    }

    public boolean delete(String id) throws SQLException {
        return czryDao.deleteByPrimaryKey(id) > 0;
    }

    public GG_CZRY getCzryByDlhMm(String dlh,String mm) throws SQLException {
        return czryDao.selectByDlhMm(dlh,mm);
    }

    public GG_CZRY getCzryByDlh(String dlh) throws SQLException {
        return czryDao.selectByDlh(dlh);
    }
}
