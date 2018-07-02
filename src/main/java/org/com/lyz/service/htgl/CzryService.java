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

    /**
     * 添加
     * @param GG_CZRY 人员信息
     * @throws SQLException 异常信息
     */
    void insert(GG_CZRY GG_CZRY)throws SQLException;

    /**
     * 修改
     * @param GG_CZRY 人员信息
     * @throws SQLException 异常信息
     */
    void update(GG_CZRY GG_CZRY) throws SQLException;

    /**
     * 删除
     * @param id 人员id
     * @throws SQLException 异常信息
     */
    void delete(String id)throws SQLException;

    /**
     * 保存
     * @param gg_czry 人员信息
     * @throws SQLException 异常信息
     */
    void save(GG_CZRY gg_czry) throws SQLException;

    /**
     * 查询
     * @param id 人员id
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    GG_CZRY selectById(String id) throws SQLException;

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
