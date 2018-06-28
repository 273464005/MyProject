package org.com.lyz.base.dao;

import org.apache.ibatis.annotations.Param;
import org.com.lyz.base.model.po.GG_CZRY;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GG_CZRYDao{
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

    /**
     * 分页查询所有用户
     * @param pageMin 开始条数
     * @param pageMax 分页大小
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String,Object>> selectAllLimit(@Param("mc") String mc,@Param("pageMin") Integer pageMin,@Param("pageMax") Integer pageMax) throws SQLException;
}