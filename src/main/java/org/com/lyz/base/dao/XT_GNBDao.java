package org.com.lyz.base.dao;

import org.apache.ibatis.annotations.Param;
import org.com.lyz.base.model.po.XT_GNB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XT_GNBDao{
    int deleteByPrimaryKey(String id) throws SQLException;

    int insert(XT_GNB record) throws SQLException;

    int insertSelective(XT_GNB record) throws SQLException;

    XT_GNB selectByPrimaryKey(String id) throws SQLException;

    int updateByPrimaryKeySelective(XT_GNB record) throws SQLException;

    int updateByPrimaryKey(XT_GNB record) throws SQLException;

    /**
     * 查询当前权限下的所有顶级功能
     * @param dyqx
     * @return
     */
    List<Map<String, Object>> selectByPrimaryKeyIsNull(@Param("dyqx") Integer dyqx) throws SQLException;

    /**
     * 根据fid和权限查询子功能
     * @param fid
     * @param dyqx
     * @return
     */
    List<Map<String, Object>> selectByFid(@Param("fid") String fid,@Param("dyqx") Integer dyqx) throws SQLException;

    /**
     * 根据FID获取最大顺序号
     * @param fid fid
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    Map<String, Object> selectMaxSxhByFid(@Param("fid") String fid) throws SQLException;

    /**
     * 获取顶级功能最大顺序号
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    Map<String,Object> selectMaxSxhByFidIsNull() throws SQLException;
}