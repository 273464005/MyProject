package org.com.lyz.constant;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/17.
 */
public class Constant_htgl {

    //---------------------------------------GG_CZRY-------------------------------------------------
    // 权限 0-超级管理员  1-管理员 2-普通用户
    public static final int GG_CZRY_QX_CJGLY = 0;
    public static final int GG_CZRY_QX_GLY = 1;
    public static final int GG_CZRY_QX_PTYH = 2;

    public static Map<Integer, String> GG_CZRY_QXMap = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_QXMap.put(GG_CZRY_QX_CJGLY, "超级管理员");
        GG_CZRY_QXMap.put(GG_CZRY_QX_GLY, "管理员");
        GG_CZRY_QXMap.put(GG_CZRY_QX_PTYH, "普通用户");
    }
    public static String getGG_CZRY_QXMap_Label(Integer qx){
        return GG_CZRY_QXMap.get(qx);
    }

    // 状态 0-启用 1-禁用
    public static final int GG_CZRY_ZT_ZC = 0;
    public static final int GG_CZRY_ZT_JY = 1;

    public static Map<Integer, String> GG_CZRY_ZTMap = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_ZTMap.put(GG_CZRY_ZT_ZC, "启用");
        GG_CZRY_ZTMap.put(GG_CZRY_ZT_JY, "禁用");
    }
    public static String getGG_CZRY_ZTMap_Label(Integer zt) {
        return GG_CZRY_ZTMap.get(zt);
    }

    // 性别 0-男 1-女
    public static final int GG_CZRY_XB_NAN = 0;
    public static final int GG_CZRY_XB_NV = 1;

    public static Map<Integer, String> GG_CZRY_XBMap = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_XBMap.put(GG_CZRY_XB_NAN, "男");
        GG_CZRY_XBMap.put(GG_CZRY_XB_NV, "女");
    }

    public static String getGG_CZRY_XBMap_Label(Integer xb) {
        return GG_CZRY_XBMap.get(xb);
    }



    //---------------------------------------------------XT_GNB-------------------------------------------
    // 状态 0-启用 1-禁用
    public static final int XT_GNB_ZT_ZC = 0;
    public static final int XT_GNB_ZT_JY = 1;

    public static Map<Integer, String> XT_GNB_ZTMap = new LinkedHashMap<Integer, String>();
    static {
        XT_GNB_ZTMap.put(XT_GNB_ZT_ZC, "启用");
        XT_GNB_ZTMap.put(XT_GNB_ZT_JY, "禁用");
    }
    public static String getXT_GNB_ZTMap_Label(Integer zt) {
        return XT_GNB_ZTMap.get(zt);
    }

}
