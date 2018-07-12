package org.com.lyz.service.htgl.impl;

import org.apache.log4j.Logger;
import org.com.lyz.base.dao.IMG_LOGDao;
import org.com.lyz.base.model.po.IMG_LOG;
import org.com.lyz.service.htgl.ImgLogService;
import org.com.lyz.util.StringUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/7/11
 */
@Service("imgLogService")
public class ImgLogServiceImpl implements ImgLogService{

    private final static Logger logger = Logger.getLogger(ImgLogServiceImpl.class);

    @Autowired
    private IMG_LOGDao logDao;

    public void insert(IMG_LOG img_log) throws SQLException {
        logDao.insertSelective(img_log);
        logger.info("----添加图片历史记录成功----");
    }

    public void save(IMG_LOG img_log) throws SQLException {
        if (StringUtils.isNotEmpty(img_log.getId())) {
            logDao.updateByPrimaryKeySelective(img_log);
        } else {
            img_log.setId(StringUtils.getUUID());
            logDao.insertSelective(img_log);
        }
        logger.info("----保存图片历史记录成功----");
    }

    public void update(IMG_LOG img_log) throws SQLException {
        logDao.updateByPrimaryKeySelective(img_log);
        logger.info("----修改图片历史记录成功----");
    }

    public void delete(IMG_LOG img_log) throws SQLException {
        logDao.deleteByPrimaryKey(img_log.getId());
        logger.info("----删除图片历史记录成功----");
    }

    public void delete(String id) throws SQLException {
        logDao.deleteByPrimaryKey(id);
        logger.info("----删除图片历史记录成功----");
    }

    /**
     * 查询所有的图片历史记录
     * @param img_log 图片历史信息
     * @param kssj 搜索开始时间
     * @param jssj 搜索结束时间
     * @param splitPageInfo 分页信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    public List<Map<String, Object>> getImgLogList(IMG_LOG img_log, Long kssj, Long jssj, SplitPageInfo splitPageInfo) throws SQLException {
        return logDao.selectAllByColumnList(img_log.getGlid(),img_log.getImgid(),kssj,jssj,splitPageInfo.getPage(),splitPageInfo.getLimit());
    }
}
