package org.com.lyz.base.dao;


import org.apache.ibatis.annotations.Param;
import org.com.lyz.base.model.po.IMG_LOG;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IMG_LOGDao {
    int deleteByPrimaryKey(String id) throws SQLException;

    int insert(IMG_LOG record) throws SQLException;

    int insertSelective(IMG_LOG record) throws SQLException;

    IMG_LOG selectByPrimaryKey(String id) throws SQLException;

    int updateByPrimaryKeySelective(IMG_LOG record) throws SQLException;

    int updateByPrimaryKey(IMG_LOG record) throws SQLException;

    /**
     * 获取所有的图片历史记录
     * @param glid 关联的id
     * @param imgid 图片id
     * @param kssj 搜索时间范围开始
     * @param jssj 搜索时间范围结束
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String, Object>> selectAllByColumnList(@Param("glid") String glid, @Param("imgid") String imgid, @Param("kssj") Long kssj, @Param("jssj") Long jssj,@Param("pageMin") Integer pageMin,@Param("pageMax") Integer pageMax) throws SQLException;
}