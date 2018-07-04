package org.com.lyz.service.htgl.impl;

import org.apache.log4j.Logger;
import org.com.lyz.base.dao.GG_IMGSDao;
import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.service.htgl.ImgService;
import org.com.lyz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author： 鲁玉震
 * @time： 2018/7/3
 */
@Service("imgService")
public class ImgServiceImpl implements ImgService{

    private static final Logger logger = Logger.getLogger(ImgService.class);

    @Autowired
    private GG_IMGSDao imgsDao;

    /**
     * 添加
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    public void insert(GG_IMGS gg_imgs) throws SQLException {
        imgsDao.insertSelective(gg_imgs);
        logger.info("----添加图片成功----");
    }

    /**
     * 保存
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    public void save(GG_IMGS gg_imgs) throws SQLException {
        if (gg_imgs.getId() != null && !"".equals(gg_imgs.getId())) {
            imgsDao.updateByPrimaryKeySelective(gg_imgs);
        } else {
            gg_imgs.setId(StringUtils.getUUID());
            imgsDao.insertSelective(gg_imgs);
        }
        logger.info("----保存图片成功----");
    }

    /**
     * 修改
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    public void update(GG_IMGS gg_imgs) throws SQLException {
        imgsDao.updateByPrimaryKeySelective(gg_imgs);
        logger.info("----修改图片成功----");
    }

    /**
     * 删除
     * @param gg_imgs 图片信息
     * @throws SQLException 异常信息
     */
    public void delete(GG_IMGS gg_imgs) throws SQLException {
        imgsDao.deleteByPrimaryKey(gg_imgs.getId());
        logger.info("----删除图片成功----");
    }

    /**
     * 删除
     * @param id 图片id
     * @throws SQLException 异常信息
     */
    public void delete(String id) throws SQLException {
        imgsDao.deleteByPrimaryKey(id);
        logger.info("----删除图片成功----");
    }

    /**
     * 根据ID查询
     * @param id 图片id
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public GG_IMGS selectById(String id) throws SQLException {
        return imgsDao.selectByPrimaryKey(id);
    }

    /**
     * 查询图片信息
     * @param gg_imgs 图片信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public GG_IMGS select(GG_IMGS gg_imgs) throws SQLException {
        return imgsDao.selectByPrimaryKey(gg_imgs.getId());
    }
}
