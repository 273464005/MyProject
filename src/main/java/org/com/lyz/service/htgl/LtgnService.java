package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.util.pageutil.SplitPageInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/9/10
 */
public interface LtgnService {

    /**
     * 获取人员信息
     * @param id 人员id
     * @return 人员信息
     */
    GG_CZRY getGg_czyb(String id) throws SQLException;

    /**
     * 获取聊天房间信息
     * @param id 房间id
     * @return 房间信息
     */
    GG_LTFJ getGg_lifj(String id) throws SQLException;

    /**
     * 保存房间信息
     * @param gg_ltfj 房间信息
     * @throws SQLException 异常信息
     */
    void saveGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException;

    /**
     * 添加房间信息
     * @param gg_ltfj 房间信息
     * @throws SQLException 异常信息
     */
    void insertGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException;

    /**
     * 保存房间人员
     * @param gg_fjryb 房间人员
     * @throws SQLException 异常信息
     */
    void saveGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException;

    /**
     * 添加房间人员
     * @param gg_fjryb 房间人员
     * @throws SQLException 异常信息
     */
    void insertGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException;

    /**
     * 修改房间信息
     * @param gg_ltfj 房间信息
     * @throws SQLException 异常信息
     */
    void updateGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException;

    /**
     * 修改房间人员
     * @param gg_fjryb 房间人员
     * @throws SQLException 异常信息
     */
    void updateGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException;

    void deleteGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException;

    void deleteGg_ltfj(String id) throws SQLException;

    void deleteGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException;

    void deleteGg_fjryb(String id) throws SQLException;

    /**
     * 获取所有的房间
     * @param SplitPageInfo 分页
     * @param gg_ltfj 参数
     * @param gg_czry 参数
     * @return 房间列表
     */
    List<Map<String,Object>> getLtfjList(SplitPageInfo SplitPageInfo,GG_LTFJ gg_ltfj,GG_CZRY gg_czry) throws SQLException;

    /**
     * 查询当前操作人是否已经有房间
     * @param gg_czry 操作人信息
     * @return 房间信息
     */
    List<Map<String,Object>> getRyfj(GG_CZRY gg_czry) throws SQLException;

    /**
     * 查询房间人员
     * @param gg_fjryb fjryb信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String,Object>> getFjry(GG_FJRYB gg_fjryb) throws SQLException;

}
