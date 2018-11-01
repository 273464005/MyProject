package org.com.lyz.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    public void sessionCreated(HttpSessionEvent se) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        HttpSession session = se.getSession();
        session.setAttribute("path",path);
        session.setAttribute("basePath",basePath);
        logger.info("session已被创建···");
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        logger.info("session已被销毁···");
    }
}
