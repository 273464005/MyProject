package org.com.lyz.base.listener;

import org.com.lyz.constant.Constants_core;
import org.com.lyz.util.ConvertUtils;
import org.com.lyz.util.MisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author： 鲁玉震
 * @time： 2018/10/18
 */
public class CustomServletListener implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(CustomServletListener.class);

    private Map<String,Object> map = new HashMap<String,Object>();

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("系统启动中···");
        try {
            String configFile = java.net.URLDecoder.decode(CustomServletListener.class.getClassLoader().getResource("prop/cfg.properties").getPath(),"utf-8");
            Properties prop = new Properties();
            prop.load(new FileInputStream(configFile));
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                map.put(key, value);
            }
            Constants_core.HEAD_PORTRAIT_FILE_PATH = ConvertUtils.createString(map.get("cfg.czry.filepath.headPortrait"));
            logger.info("加载头像文件上传路径[" + Constants_core.HEAD_PORTRAIT_FILE_PATH + "]");
            Constants_core.FOOTER_SHOW_EMAIL = ConvertUtils.createString(map.get("cfg.html.footer.showEmail"));
            logger.info("加载固定区域展示邮箱[" + Constants_core.FOOTER_SHOW_EMAIL + "]");
            Constants_core.FOOTER_SHOW_GITHUB = ConvertUtils.createString(map.get("cfg.html.footer.showGitHub"));
            logger.info("加载固定区域展示下载链接[" + Constants_core.FOOTER_SHOW_GITHUB + "]");
        } catch (Exception e) {
            throw new MisException(e);
        }
    }
    public void contextDestroyed(ServletContextEvent sce) {

        logger.info("系统关闭。");
    }
}
