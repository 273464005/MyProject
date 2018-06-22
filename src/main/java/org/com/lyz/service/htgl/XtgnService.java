package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.XT_GNB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 系统功能接口
 */
public interface XtgnService {
   /**
    * 根据权限查询当前功能
    * @param dyqx 权限
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   List<Map<String,Object>> getXtgnList(Integer dyqx) throws SQLException;

   /**
    * 根据id查询功能
    * @param id 功能id
    * @return 查询结果
    */
   XT_GNB getXt_gnbById(String id) throws SQLException;

   /**
    * 根据FID获取最大顺序号
    * @param fid fid
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   Map<String, Object> getMaxSxhByFid(String fid) throws SQLException;

   /**
    * 获取顶级功能的最大顺序号
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   Map<String, Object> getMaxSxh() throws SQLException;

   /**
    * 保存 xt_gnb
    * @param xt_gnb 功能信息
    * @throws SQLException 异常信息
    */
   void saveXt_gnb(XT_GNB xt_gnb) throws SQLException;

   /**
    * 保存 xt_gnb 需要手动为ID赋值
    * @param xt_gnb 功能信息
    * @throws SQLException 异常信息
    */
   void insertXt_gnb(XT_GNB xt_gnb) throws SQLException;

   /**
    * 根据权限获取所有的FID为null的功能
    * @param dyqx 对应权限
    * @return 查询结果
    */
   List<Map<String,Object>> getFatherGnbList(Integer dyqx) throws SQLException;

   /**
    * 获取当前父ID下面的所有子功能
    * @param fid FID
    * @param dyqx 对应权限
    * @return 查询结果
    */
   List<Map<String, Object>> getChildrenGbnList(String fid,Integer dyqx) throws SQLException;
}
