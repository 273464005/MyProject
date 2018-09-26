package org.com.lyz.base.dao;


import org.apache.ibatis.annotations.Param;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GG_LTFJDao {
    int deleteByPrimaryKey(String id);

    int insert(GG_LTFJ record);

    int insertSelective(GG_LTFJ record);

    GG_LTFJ selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GG_LTFJ record);

    int updateByPrimaryKey(GG_LTFJ record);

    /**
     * 查询所有的房间列表
     * @param zt 房间状态
     * @param cjrmc 创建人名称（或者是房间号）
     * @param pageMin 分页
     * @param pageMax 分页
     * @return 房间列表
     */
    List<Map<String,Object>> selectAllLtfjByZt(@Param("fjzt") Integer zt,@Param("cjrmc") String cjrmc,@Param("pageMin") Integer pageMin,@Param("pageMax") Integer pageMax);

    /**
     * 根据人员id查询房间
     * @param cjr 创建人
     * @return 房间
     */
    List<Map<String,Object>> selectLtfjByCjr(@Param("cjr") String cjr);
}