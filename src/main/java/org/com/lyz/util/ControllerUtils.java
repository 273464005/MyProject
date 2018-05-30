package org.com.lyz.util;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 重定向页面
 * Created by Administrator on 2018/5/28.
 */
public class ControllerUtils {

    /**
     * 页面转发
     * @param dz 转发页面需要带后缀
     * @return
     */
    public static ModelAndView getForward(String dz){
        ModelAndView mv = new ModelAndView("forward:/"+dz);
        return mv;
    }

    /**
     * 页面转发
     * @param dz 转发的地址
     * @return
     */
    public static String getStringForward(String dz){
        return "forward:/"+dz;
    }

    /**
     * forward重定向
     * 服务器内部重定向
     * 地址栏不改变
     * @param dz 重定向后指向的地址
     * @return
     */
    public static ModelAndView getForwardToJsp(String dz){
        ModelAndView mv = new ModelAndView("forward:/"+dz+".jsp");
        return mv;
    }

    /**
     * forward转发
     * 服务器内部转发
     * 地址栏不改变
     * @param dz 重定向后指向的地址
     * @param map 参数
     * @return
     */
    public static ModelAndView getForwardToJsp(String dz,Map<String,Object> map){
        ModelAndView mv = new ModelAndView("forward:/"+dz+".jsp");
        ergodicMap(mv,map);
        return mv;
    }

    /**
     * forward转发
     * 服务器内部转发
     * 地址栏不改变
     * @param dz 转发后指向的地址
     * @return 转发后的地址
     */
    public static ModelAndView getForwardToHtml(String dz){
        ModelAndView mv = new ModelAndView("forward:/"+dz+".html");
        return mv;
    }

    /**
     * forward转发
     * 服务器内部转发
     * 地址栏不改变
     * @param dz 转发后指向的地址
     * @param map 转发参数
     * @return 转发后的地址
     */
    public static ModelAndView getForwardToHtml(String dz,Map<String,Object> map){
        ModelAndView mv = new ModelAndView("forward:/"+dz+".html");
        ergodicMap(mv,map);
        return mv;
    }

    /**
     * 页面重定向
     * @param dz 转发地址，页面需要带后缀
     * @return
     */
    public static ModelAndView getRedirect(String dz){
        ModelAndView mv = new ModelAndView("redirect:/"+dz);
        return mv;
    }

    /**
     * 页面重定向
     * @param dz
     * @return
     */
    public static String getStringRedirect(String dz){
        return "redirect:/" + dz;
    }

    /**
     * redirect重定向
     * 客户端重定向
     * 地址栏改变
     * @param dz 重定向后指向的地址
     * @return 重定向后的地址
     */
    public static ModelAndView getRedirectToJsp(String dz){
        ModelAndView mv = new ModelAndView("redirect:/"+dz+".jsp");
        return mv;
    }

    /**
     * redirect重定向
     * 客户端重定向
     * 地址栏改变
     * @param dz 重定向后指向的地址
     * @param map 参数
     * @return 重定向后的地址
     */
    public static ModelAndView getRedirectToJsp(String dz,Map<String,Object> map){
        ModelAndView mv = new ModelAndView("redirect:/"+dz+".jsp");
        ergodicMap(mv,map);
        return mv;
    }

    /**
     * redirect重定向
     * 客户端重定向
     * 地址栏改变
     * @param dz 重定向后指向的地址
     * @return 重定向后的地址
     */
    public static ModelAndView getRedirectToHtml(String dz){
        ModelAndView mv = new ModelAndView("redirect:/"+dz+".html");
        return mv;
    }

    /**
     * redirect重定向
     * 客户端重定向
     * 地址栏改变
     * @param dz 重定向后指向的地址
     * @param map 重定向后的参数
     * @return
     */
    public static ModelAndView getRedirectToHtml(String dz,Map<String,Object> map){
        ModelAndView mv = new ModelAndView("redirect:/"+dz+".html");
        ergodicMap(mv,map);
        return mv;
    }

    /**
     * 遍历map赋值
     * @param mv
     * @param map
     */
    private static void ergodicMap(ModelAndView mv,Map<String,Object> map){
        if (map != null){
            for (Map.Entry<String,Object> entry:map.entrySet()){
                mv.addObject(ConvertUtils.createString(entry.getKey()),entry.getValue());
            }
        }
    }

}
