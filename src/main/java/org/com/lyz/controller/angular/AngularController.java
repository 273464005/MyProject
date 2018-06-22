package org.com.lyz.controller.angular;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.XtgnService;
import org.com.lyz.util.returnvalue.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author： 鲁玉震
 * @time： 2018/6/15
 */
@Controller
@RequestMapping("angular")
public class AngularController {

    @Autowired
    private CzryService czryService;

    @Autowired
    private XtgnService xtgnService;


    /**
     * angularJS提交方式
     * --@RequestBody 处理angularJS传过来的数据，Content-Type为 application/json
     *  jquery请求的"Content-Type"默认为" application/x-www-form-urlencoded"，
     * @param request 请求信息
     * @param gg_czry 登陆信息
     * @return 登陆结果
     */
    @RequestMapping("/csaction")
    @ResponseBody
    public ReturnValue csAction(HttpServletRequest request, @RequestBody GG_CZRY gg_czry){
        Map<String, Object> returnMap = new HashMap<String,Object>();
        if (gg_czry != null) {
            try {
                GG_CZRY czry = czryService.getCzryByDlh(gg_czry.getDlh());
                if(czry == null){
                    return ReturnValue.newErrorInstance();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ReturnValue.newErrorInstance();
    }
}
