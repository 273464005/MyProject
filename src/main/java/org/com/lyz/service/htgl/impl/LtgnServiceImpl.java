package org.com.lyz.service.htgl.impl;

import org.apache.log4j.Logger;
import org.com.lyz.base.dao.GG_CZRYDao;
import org.com.lyz.base.dao.GG_FJRYBDao;
import org.com.lyz.base.dao.GG_LTFJDao;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.constant.Constant_htgl;
import org.com.lyz.constant.Constant_ltyl;
import org.com.lyz.service.htgl.LtgnService;
import org.com.lyz.util.DateUtil;
import org.com.lyz.util.MisException;
import org.com.lyz.util.StringUtils;
import org.com.lyz.util.pageutil.SplitPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/9/10
 */
@Service("ltgnService")
public class LtgnServiceImpl implements LtgnService {

    private final static Logger logger = Logger.getLogger(LtgnServiceImpl.class);

    @Autowired
    private GG_CZRYDao czryDao;

    @Autowired
    private GG_LTFJDao ltfjDao;

    @Autowired
    private GG_FJRYBDao fjrybDao;

    /**
     * 人员信息
     * @param id 人员id
     * @return 人员信息
     * @throws SQLException
     */
    public GG_CZRY getGg_czyb(String id) throws SQLException{
        return czryDao.selectByPrimaryKey(id);
    }

    /**
     * 房间信息
     * @param id 房间id
     * @return 房间信息
     * @throws SQLException
     */
    public GG_LTFJ getGg_lifj(String id) throws SQLException{
        return ltfjDao.selectByPrimaryKey(id);
    }

    public void saveGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException {
        if (gg_ltfj != null){
            if (StringUtils.isNotEmpty(gg_ltfj.getId())){
                this.updateGg_ltfj(gg_ltfj);
            } else {
                gg_ltfj.setId(StringUtils.getUUID());
                this.insertGg_ltfj(gg_ltfj);
            }
            logger.info("保存gg_ltfj成功！");
        } else {
            throw new  MisException("保存失败！gg_ltfj不能为空！");
        }
    }

    public void insertGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException {
        gg_ltfj.setCjsj(DateUtil.getLongCurrDateTime14());
        ltfjDao.insert(gg_ltfj);
        logger.info("添加gg_ltfj成功！");
    }

    public void saveGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException {
        if (gg_fjryb != null){
            if (StringUtils.isNotEmpty(gg_fjryb.getId())){
                this.updateGg_fjryb(gg_fjryb);
            } else {
                gg_fjryb.setId(StringUtils.getUUID());
                this.insertGg_fjryb(gg_fjryb);
            }
            logger.info("保存gg_fjryb成功！");
        } else {
            throw new MisException("保存失败！gg_fjryb不能为空");
        }
    }

    public void insertGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException {
        fjrybDao.insert(gg_fjryb);
        logger.info("添加gg_fjryb成功！");
    }

    public void updateGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException {
        ltfjDao.updateByPrimaryKeySelective(gg_ltfj);
        logger.info("修改gg_ltfj成功！");
    }

    public void updateGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException {
        fjrybDao.updateByPrimaryKeySelective(gg_fjryb);
        logger.info("修改gg_fjryb成功！");
    }

    public void deleteGg_ltfj(GG_LTFJ gg_ltfj) throws SQLException {
        this.deleteGg_ltfj(gg_ltfj.getId());
        logger.info("删除gg_ltfj成功！");
    }

    public void deleteGg_ltfj(String id) throws SQLException {
        ltfjDao.deleteByPrimaryKey(id);
        logger.info("删除gg_ltfj成功！");
    }

    public void deleteGg_fjryb(GG_FJRYB gg_fjryb) throws SQLException {
        this.deleteGg_fjryb(gg_fjryb.getId());
        logger.info("删除gg_fjryb成功！");
    }

    public void deleteGg_fjryb(String id) throws SQLException {
        fjrybDao.deleteByPrimaryKey(id);
        logger.info("删除gg_fjryb成功！");
    }

    /**
     * 获取所有的房间
     * @param splitPageInfo 分页
     * @param gg_ltfj 参数
     * @return 房间列表
     */
    public List<Map<String, Object>> getLtfjList(SplitPageInfo splitPageInfo, GG_LTFJ gg_ltfj,GG_CZRY gg_czry) throws SQLException {
        String cjrmc = null;
        if (gg_ltfj != null){
            if (StringUtils.isNotEmpty(gg_ltfj.getCjrmc())){
                cjrmc = "%" + gg_ltfj.getCjrmc() + "%";
            }
        }
        List<Map<String,Object>> list;
        if (gg_czry.getQx() <= Constant_htgl.GG_CZRY_QX_GLY){
            list = ltfjDao.selectAllLtfjByZt(null,cjrmc,splitPageInfo.getPage(),splitPageInfo.getLimit());
        } else {
            list = ltfjDao.selectAllLtfjByZt(Constant_ltyl.GG_LTFJ_FJZT_ZC,cjrmc,splitPageInfo.getPage(),splitPageInfo.getLimit());
        }
        return list;
    }

    /**
     * 查询当前操作人是否已经有房间
     * @param gg_czry 操作人信息
     * @return 房间信息
     */
    public List<Map<String, Object>> getRyfj(GG_CZRY gg_czry) throws SQLException {
        List<Map<String,Object>> list = ltfjDao.selectLtfjByCjr(gg_czry.getId());
        return list;
    }

    /**
     * 查询房间人员
     * @param gg_fjryb fjryb信息
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> getFjry(GG_FJRYB gg_fjryb) throws SQLException {
        return fjrybDao.selectAllByFjidOrRyid(gg_fjryb.getFjid(),gg_fjryb.getRyid());
    }
}
