package org.com.lyz.base.dao.basedao;

import org.apache.ibatis.annotations.Insert;
import org.com.lyz.base.model.basepo.BasePo;

import java.sql.SQLException;

/**
 * 作者： 鲁玉震
 * 创建时间： 2018/5/31
 */
public interface BaseDao {

    <T extends BasePo<T>> int insert(T basePo) throws SQLException;

    <T extends BasePo<T>> int insertSelective(T basePo) throws SQLException;

    <T extends BasePo<T>> int updateByPrimaryKeySelective(T basePo) throws SQLException;

    <T extends BasePo<T>> int updateByPrimaryKey(T basePo) throws SQLException;

    <T extends BasePo<T>> int deleteByPrimaryKey(String id) throws SQLException;

    <T extends BasePo<T>> T selectByPrimaryKey(T po) throws SQLException;


}
