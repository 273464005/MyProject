package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.IMG_LOG;
import org.com.lyz.util.pageutil.SplitPageInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/7/11
 */
public interface ImgLogService {

    void insert(IMG_LOG img_log) throws SQLException;

    void save(IMG_LOG img_log) throws SQLException;

    void update(IMG_LOG img_log) throws SQLException;

    void delete(IMG_LOG img_log) throws SQLException;

    void delete(String id) throws SQLException;

    /**
     * 查询所有的图片历史信息
     * @param img_log 图片历史信息
     * @param kssj 搜索开始时间
     * @param jssj 搜索结束时间
     * @param splitPageInfo 分页信息
     * @return 查询结果
     * @throws SQLException 异常信息
     */
    List<Map<String,Object>> getImgLogList(IMG_LOG img_log, Long kssj, Long jssj, SplitPageInfo splitPageInfo) throws SQLException;

}
