package org.com.lyz.base.dao;

import org.com.lyz.base.model.po.GG_CZRY;

public interface GG_CZRYDao {
    int deleteByPrimaryKey(String id);

    int insert(GG_CZRY record);

    int insertSelective(GG_CZRY record);

    GG_CZRY selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GG_CZRY record);

    int updateByPrimaryKey(GG_CZRY record);

    /**
     * 根据登录号,密码查询
     * @param dlh
     * @return
     */
    GG_CZRY selectByDlhMm(String dlh,String mm);

    /**
     * 根据登录号查询
     * @param dlh
     * @return
     */
    GG_CZRY selectByDlh(String dlh);
}