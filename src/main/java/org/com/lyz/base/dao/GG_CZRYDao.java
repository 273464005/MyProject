package org.com.lyz.base.dao;

import org.com.lyz.base.dao.basedao.BaseDao;
import org.com.lyz.base.model.po.GG_CZRY;

import java.sql.SQLException;

public interface GG_CZRYDao extends BaseDao{
    int deleteByPrimaryKey(String id) throws SQLException;

    int insert(GG_CZRY record) throws SQLException;

    int insertSelective(GG_CZRY record) throws SQLException;

    GG_CZRY selectByPrimaryKey(String id) throws SQLException;

    int updateByPrimaryKeySelective(GG_CZRY record) throws SQLException;

    int updateByPrimaryKey(GG_CZRY record) throws SQLException;

    /**
     * 根据登录号,密码查询
     * @param dlh
     * @return
     */
    GG_CZRY selectByDlhMm(String dlh,String mm) throws SQLException;

    /**
     * 根据登录号查询
     * @param dlh
     * @return
     */
    GG_CZRY selectByDlh(String dlh) throws SQLException;
}