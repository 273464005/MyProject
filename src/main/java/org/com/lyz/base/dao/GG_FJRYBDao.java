package org.com.lyz.base.dao;


import org.apache.ibatis.annotations.Param;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface GG_FJRYBDao {
    int deleteByPrimaryKey(String id) throws SQLException;

    int insert(GG_FJRYB record) throws SQLException;

    int insertSelective(GG_FJRYB record) throws SQLException;

    GG_FJRYB selectByPrimaryKey(String id) throws SQLException;

    int updateByPrimaryKeySelective(GG_FJRYB record) throws SQLException;

    int updateByPrimaryKey(GG_FJRYB record) throws SQLException;

    /**
     * 根据房间id或人员id查询房间人员
     * @param fjid 房间id
     * @param ryid 人员id
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String,Object>> selectAllByFjidOrRyid(@Param("fjid") String fjid, @Param("ryid") String ryid) throws SQLException;
}