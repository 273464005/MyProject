package org.com.lyz.service.htglservice;

import org.com.lyz.base.model.po.GG_CZRY;

/**
 * Created by Administrator on 2018/5/17.
 */
public interface CzryService {

    boolean insert(GG_CZRY GG_CZRY);

    boolean update(GG_CZRY GG_CZRY);

    boolean delete(String id);

    /**
     * 根据登录号，密码查询操作人员
     * @param dlh
     * @return
     */
    GG_CZRY getCzryByDlhMm(String dlh,String mm);

    /**
     * 根据登录号查询操作人员
     * @param dlh
     * @return
     */
    GG_CZRY getCzryByDlh(String dlh);
}
