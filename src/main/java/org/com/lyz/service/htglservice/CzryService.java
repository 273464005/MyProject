package org.com.lyz.service.htglservice;

import org.com.lyz.base.model.po.GG_CZRY;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/5/17.
 */
public interface CzryService {

    boolean insert(GG_CZRY GG_CZRY)throws SQLException;

    boolean update(GG_CZRY GG_CZRY) throws SQLException;

    boolean delete(String id)throws SQLException;

    /**
     * 根据登录号，密码查询操作人员
     * @param dlh
     * @return
     */
    GG_CZRY getCzryByDlhMm(String dlh,String mm)throws SQLException;

    /**
     * 根据登录号查询操作人员
     * @param dlh
     * @return
     */
    GG_CZRY getCzryByDlh(String dlh)throws SQLException;
}
