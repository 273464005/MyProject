package org.com.lyz.base.service.Impl;

import org.com.lyz.base.dao.basedao.BaseDao;
import org.com.lyz.base.model.basepo.BasePo;
import org.com.lyz.base.service.BaseService;
import org.com.lyz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

/**
 * 作者： 鲁玉震
 * 创建时间： 2018/5/31
 */
public class BaseServiceImpl implements BaseService{

    @Autowired
    private BaseDao dao;

    public <T extends BasePo<T>> int insert(T basePo) throws SQLException {
        return this.dao.insert(basePo);
    }

    public <T extends BasePo<T>> int insertSelective(T basePo) throws SQLException {
        return this.dao.insertSelective(basePo);
    }

    public <T extends BasePo<T>> int updateByPrimaryKeySelective(T basePo) throws SQLException {
        return this.dao.updateByPrimaryKeySelective(basePo);
    }

    public <T extends BasePo<T>> int updateByPrimaryKey(T basePo) throws SQLException {
        return this.dao.updateByPrimaryKey(basePo);
    }

    public <T extends BasePo<T>> int deleteByPrimaryKey(String id) throws SQLException {
        return this.dao.deleteByPrimaryKey(id);
    }

    public <T extends BasePo<T>> T selectByPrimaryKey(T po) throws SQLException {
        return this.dao.selectByPrimaryKey(po);
    }

    public <T extends BasePo<T>> int save(T po) throws SQLException {
        po.setPkValue(StringUtils.getUUID());
        return this.dao.insertSelective(po);
    }

    public <T extends BasePo<T>> int update(T po) throws SQLException {
        return this.dao.updateByPrimaryKeySelective(po);
    }

    public <T extends BasePo<T>> int deleteById(String id) throws SQLException {
        return this.dao.deleteByPrimaryKey(id);
    }

    public <T extends BasePo<T>> T selectById(T po) throws SQLException {
        return this.dao.selectByPrimaryKey(po);
    }
}
