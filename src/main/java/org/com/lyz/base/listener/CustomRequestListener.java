package org.com.lyz.base.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author： 鲁玉震
 * @time： 2018/10/18
 */
public class CustomRequestListener implements ServletRequestListener {

    private final static Logger logger = Logger.getLogger(CustomRequestListener.class);

    /**
     * 请求销毁时执行
     * @param sre
     */
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    /**
     * 请求到达，初始化的时候执行
     * @param sre
     */
    public void requestInitialized(ServletRequestEvent sre) {

    }
}
