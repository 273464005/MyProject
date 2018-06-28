package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.util.pageutil.SplitPageInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 操作人员接口
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

    /**
     * 分页查询所有操作人员
     * @param splitPageInfo 分页信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String,Object>> getAllCzryLimit(String mc,SplitPageInfo splitPageInfo) throws SQLException;
}
