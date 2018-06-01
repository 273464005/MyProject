package org.com.lyz.base.service;

import org.com.lyz.base.dao.basedao.BaseDao;
import org.com.lyz.base.model.basepo.BasePo;

import java.sql.SQLException;

/**
 * 作者： 鲁玉震
 * 创建时间： 2018/5/31
 */
public interface BaseService extends BaseDao{

    /**
     * 添加，不用给ID赋值
     * @param po
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BasePo<T>> int save(T po) throws SQLException;

    /**
     * 修改
     * @param po
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BasePo<T>> int update(T po) throws SQLException;

    /**
     * 根据ID删除
     * @param id
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BasePo<T>> int deleteById(String id) throws SQLException;

    /**
     * 根据ID查询
     * @param id
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BasePo<T>> T selectById(T po) throws SQLException;
}
