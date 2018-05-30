package org.com.lyz.service.htglservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.com.lyz.base.dao.XT_GNBDao;
import org.com.lyz.service.htglservice.XtgnService;
import org.com.lyz.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/10.
 */
@Service("xtgnService")
public class XtgnServiceImpl implements XtgnService{

	private static final Logger logger = Logger.getLogger(XtgnServiceImpl.class);

    @Autowired
    private XT_GNBDao xtgnDao;
    
    public List<Map<String, Object>> getXtgnList(Integer dyqx) {
        List<Map<String,Object>> xtgnList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> fatherList = xtgnDao.selectByPrimaryKeyIsNull(dyqx);
        logger.info("获取功能列表，共"+fatherList.size()+"条");
        for (Map<String, Object> fMap : fatherList) {
            List<Map<String, Object>> childrenList = xtgnDao.selectByFid(ConvertUtils.createString(fMap.get("id")),dyqx);
            logger.info("获取子功能列表，共"+childrenList.size()+"条");
            fMap.put("childrenList",childrenList);
            xtgnList.add(fMap);
        }
        return xtgnList;
    }
}
