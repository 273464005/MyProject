package org.com.lyz.base.listener;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author： 鲁玉震
 * @time： 2018/10/18
 */
public class CustomSessionListener implements HttpSessionListener {

    private final static Logger logger = Logger.getLogger(CustomSessionListener.class);

    public void sessionCreated(HttpSessionEvent se) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        //初始化的时候，将项目的相对路径和绝对路径存入session中
//        request.getSession().setAttribute("path",path);
//        request.getSession().setAttribute("basePath",basePath);
        HttpSession session = se.getSession();
        session.setAttribute("path",path);
        session.setAttribute("basePath",basePath);
        logger.info("====成功加载sessionCreated方法====");
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        logger.info("====成功加载sessionDestroyed方法====");
    }
}
