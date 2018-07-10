package org.com.lyz.util;

import net.sf.json.JSONArray;
import org.com.lyz.base.model.po.GG_IMGS;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * layui  layer的工具类
 *
 * @author： 鲁玉震
 * @time： 2018/7/5
 */
public class LayerUtils {

    /**
     * 获取相册map
     * @param request 请求信息
     * @param imgMap 图片信息
     * @param map 相册信息
     * @return 结果
     */
    public static Map<String, Object> getBigImgMap(HttpServletRequest request,Map<String,Object> imgMap,Map<String,Object> map){
        if(imgMap != null && imgMap.size() > 0){
            String  imgPath = FileUtils.getZxImgPath(request)+imgMap.get("tpmc");
            String thumb = "";
            String title = null;
            String id = null;
            String start = null;
            if (map != null && map.size() > 0) {
                thumb = ConvertUtils.createString(map.get("thumb"));
                title = ConvertUtils.createString(map.get("title"));
                id = ConvertUtils.createString(map.get("id"));
                start = ConvertUtils.createString(map.get("start"));
            }
            Map<String, Object> dataMap = getDataMap(ConvertUtils.createString(imgMap.get("id")),
                    ConvertUtils.createString(imgMap.get("tpmc")), imgPath, thumb);
            JSONArray dataJson = JSONArray.fromObject(dataMap);
            return getAlbumMap(title, id, start, dataJson);
        }
        return null;
    }

    /**
     * 获取相册map
     * @param request 请求信息
     * @param gg_imgs 图片信息
     * @param map 相册信息
     * @return 结果
     */
    public static Map<String,Object> getBigImgMap(HttpServletRequest request,GG_IMGS gg_imgs,Map<String,Object> map){
        Map<String, Object> imgMap = new HashMap<String, Object>();
        imgMap.put("id", gg_imgs.getId());
        imgMap.put("tpmc", gg_imgs.getTpmc());
        return getBigImgMap(request,imgMap,map);
    }

    /**
     * 获取相册map
     * @param request 请求信息
     * @param gg_imgs 图片信息
     * @return 结果
     */
    public static Map<String,Object> getBigImgMap(HttpServletRequest request,GG_IMGS gg_imgs){
        return getBigImgMap(request, gg_imgs, null);
    }
    /**
     * 获取相册map
     * @param request 请求信息
     * @param imgMap 图片信息
     * @return 结果
     */
    public static Map<String,Object> getBigImgMap(HttpServletRequest request,Map<String, Object> imgMap){
        return getBigImgMap(request, imgMap, null);
    }

    /**
     * 组织相册map
     *
     * @param title 标题
     * @param id    id
     * @param start 初始展示
     * @param data  图片组
     * @return 组织结果
     */
    private static Map<String, Object> getAlbumMap(String title, String id, String start, JSONArray data) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(title)) {
            title = "";
        }
        if (StringUtils.isEmpty(id)) {
            id = "1";
        }
        if (StringUtils.isEmpty(start)) {
            start = "0";
        }
        returnMap.put("title", title);
        returnMap.put("id", id);
        returnMap.put("start", start);
        returnMap.put("data", data);
        return returnMap;
    }

    /**
     * 组织图片map
     *
     * @param pid   图片id
     * @param alt   图片名称
     * @param src   图片地址
     * @param thumb 缩略图地址
     * @return 组织结果
     */
    private static Map<String, Object> getDataMap(String pid, String alt, String src, String thumb) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("pid", pid);
        returnMap.put("alt", alt);
        returnMap.put("src", src);
        returnMap.put("thumb", thumb);
        return returnMap;
    }
}
