package org.com.lyz.base.dao;


import org.com.lyz.base.model.po.GG_IMGS;

import java.sql.SQLException;

public interface GG_IMGSDao {
    int deleteByPrimaryKey(String id) throws SQLException;

    int insert(GG_IMGS record) throws SQLException;

    int insertSelective(GG_IMGS record) throws SQLException;

    GG_IMGS selectByPrimaryKey(String id) throws SQLException;

    int updateByPrimaryKeySelective(GG_IMGS record) throws SQLException;

    int updateByPrimaryKey(GG_IMGS record) throws SQLException;
}