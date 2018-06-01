package org.com.lyz.service.htglservice;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */
public interface XtgnService {
   List<Map<String,Object>> getXtgnList(Integer dyqx) throws SQLException;
}
