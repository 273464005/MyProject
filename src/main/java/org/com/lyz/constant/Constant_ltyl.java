package org.com.lyz.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/9/20
 */
public class Constant_ltyl {

    //房间状态   0-正常   1-禁用
    public static final Integer GG_LTFJ_FJZT_ZC = 0;
    public static final Integer GG_LTFJ_FJZT_JY = 1;
    public static final Map<Integer,String> GG_LTFJ_FJZT_MAP = new LinkedHashMap<Integer, String>();
    static {
        GG_LTFJ_FJZT_MAP.put(GG_LTFJ_FJZT_ZC,"正常");
        GG_LTFJ_FJZT_MAP.put(GG_LTFJ_FJZT_JY,"禁用");
    }
    public static final Map<Integer,String> getGG_LTFJ_FJZT_MAP(){
        return GG_LTFJ_FJZT_MAP;
    }
    public static final String getGG_LTFJ_FJZT_MAP_LABLE(Integer fjzt){
        return GG_LTFJ_FJZT_MAP.get(fjzt);
    }

    //房间描述，默认
    public static final String GG_LTFJ_FJMS_DEFAULT = "房主很懒，没有留下什么";
}
