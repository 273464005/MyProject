package org.com.lyz.service.htgl;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.XT_GNB;
import org.com.lyz.util.pageutil.SplitPageInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 系统功能接口
 */
public interface XtgnService {
   /**
    * 根据权限查询当前功能
    * @param xt_gnb 功能信息
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   List<Map<String,Object>> getXtgnList(XT_GNB xt_gnb) throws SQLException;

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
    * 修改
    * @param xt_gnb 功能信息
    * @throws SQLException 异常信息
    */
   void updateXt_gnb(XT_GNB xt_gnb) throws SQLException;

   /**
    * 删除
    * @param xt_gnb 功能信息
    * @throws SQLException 异常信息
    */
   void deleteXt_gnb(XT_GNB xt_gnb) throws SQLException;

   /**
    * 删除
    * @param gnid 功能id
    * @throws SQLException 异常信息
    */
   void deleteXt_gnb(String gnid) throws SQLException;

   /**
    * 根据权限获取所有的FID为null的功能
    * @param xt_gnb 功能信息
    * @return 查询结果
    */
   List<Map<String,Object>> getFatherGnbList(XT_GNB xt_gnb) throws SQLException;

   /**
    * 根据权限获取所有的FID不为null的功能
    * @param xt_gnb 功能信息
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   List<Map<String,Object>> getAllChildrenGnbList(XT_GNB xt_gnb) throws SQLException;

   /**
    * 获取当前父ID下面的所有子功能
    * @param xt_gnb 功能信息
    * @return 查询结果
    */
   List<Map<String, Object>> getChildrenGbnList(XT_GNB xt_gnb) throws SQLException;

   /**
    * 分页查询
    * @param xt_gnb 功能信息
    * @param gg_czry 人员信息
    * @param splitPageInfo 分页信息
    * @return 查询结果
    * @throws SQLException 异常信息
    */
   List<Map<String, Object>> getAllGnbLimit(GG_CZRY gg_czry,XT_GNB xt_gnb, SplitPageInfo splitPageInfo) throws SQLException;
}
