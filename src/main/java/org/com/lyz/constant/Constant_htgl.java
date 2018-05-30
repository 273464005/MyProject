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

    public static Map<Integer, String> GG_CZRY_QX = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_QX.put(GG_CZRY_QX_CJGLY, "超级管理员");
        GG_CZRY_QX.put(GG_CZRY_QX_GLY, "管理员");
        GG_CZRY_QX.put(GG_CZRY_QX_PTYH, "普通用户");
    }
    public static String getGG_CZRY_QX_Label(Integer qx){
        return GG_CZRY_QX.get(qx);
    }

    // 状态 0-正常 1-禁用
    public static final int GG_CZRY_ZT_ZC = 0;
    public static final int GG_CZRY_ZT_JY = 1;

    public static Map<Integer, String> GG_CZRY_ZT = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_ZT.put(GG_CZRY_ZT_ZC, "正常");
        GG_CZRY_ZT.put(GG_CZRY_ZT_JY, "禁用");
    }
    public static String getGG_CZRY_ZT_Label(Integer zt) {
        return GG_CZRY_ZT.get(zt);
    }

    // 性别 0-男 1-女
    public static final int GG_CZRY_XB_NAN = 0;
    public static final int GG_CZRY_XB_NV = 1;

    public static Map<Integer, String> GG_CZRY_XB = new LinkedHashMap<Integer, String>();
    static {
        GG_CZRY_XB.put(GG_CZRY_XB_NAN, "男");
        GG_CZRY_XB.put(GG_CZRY_XB_NV, "女");
    }

    public static String getGG_CZRY_XB_Label(Integer xb) {
        return GG_CZRY_XB.get(xb);
    }



    //---------------------------------------------------XT_GNB-------------------------------------------
    // 状态 0-正常 1-禁用
    public static final int XT_GNB_ZT_ZC = 0;
    public static final int XT_GNB_ZT_JY = 1;

    public static Map<Integer, String> XT_GNB_ZT = new LinkedHashMap<Integer, String>();
    static {
        XT_GNB_ZT.put(XT_GNB_ZT_ZC, "正常");
        XT_GNB_ZT.put(XT_GNB_ZT_JY, "禁用");
    }
    public static String getXT_GNB_ZT_Label(Integer zt) {
        return XT_GNB_ZT.get(zt);
    }

}
