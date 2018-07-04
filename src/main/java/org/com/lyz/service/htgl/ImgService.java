package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.GG_IMGS;

import java.sql.SQLException;

/**
 * @author： 鲁玉震
 * @time： 2018/7/3
 */
public interface ImgService {

    /**
     * 添加
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    void insert(GG_IMGS gg_imgs)  throws SQLException;

    /**
     * 保存
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    void save(GG_IMGS gg_imgs) throws SQLException;

    /**
     * 修改
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    void update(GG_IMGS gg_imgs) throws SQLException;

    /**
     * 删除
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    void delete(GG_IMGS gg_imgs) throws SQLException;

    /**
     * 删除
     * @param id 图片id
     * @throws SQLException 异常信息
     */
    void delete(String id) throws SQLException;

    /**
     * 根据id查询
     * @param id 图片id
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    GG_IMGS selectById(String id) throws SQLException;

    /**
     * 查询
     * @param gg_imgs 图片信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    GG_IMGS select(GG_IMGS gg_imgs) throws SQLException;
}
