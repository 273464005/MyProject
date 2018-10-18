package org.com.lyz.base.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author： 鲁玉震
 * @time： 2018/10/18
 */
public class CustomServletListener implements ServletContextListener {

    private final static Logger logger = Logger.getLogger(CustomServletListener.class);

    public void contextInitialized(ServletContextEvent sce) {

        logger.info("====成功加载contextInitialized方法====");
    }

    public void contextDestroyed(ServletContextEvent sce) {

        logger.info("====成功加载contextDestroyed方法====");
    }
}
