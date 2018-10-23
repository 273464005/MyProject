package org.com.lyz.util;

import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.base.model.po.IMG_LOG;
import org.com.lyz.constant.Constants_core;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.ImgLogService;
import org.com.lyz.service.htgl.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * 文件上传工具类
 * @author： 鲁玉震
 * @time： 2018/7/3
 */
@Component
public class FileUtils {

    private final static Logger logger = Logger.getLogger(FileUtils.class);


    /**
     * 修改文件名字，获取随机名字
     * @param fileName 原始的文件名称
     * @return 修改后的名称
     */
    public static String getFileRandomName(String fileName){
        String randomFileName = DateUtil.getLongCurrDateTime14() + "-" +Integer.toHexString(new Random().nextInt()) + "." + fileName.
                substring(fileName.lastIndexOf(".") + 1);
        return randomFileName;
    }

    /**
     * 上传图片
     * @param czyPath 操作员id（拼接到路径上，每个人的头像单独存放一个文件夹）
     * @param fileName 图片名称
     * @return 上传结果
     */
    public static File upload(String czyPath,String fileName){
        String pat = Constants_core.HEADPORTRAITFILEPATH  + getSlash() +czyPath;
        File fileDir=new File(pat);
        if (!fileDir.exists()) { //如果目录不存在 则创建
            fileDir.mkdirs();
            logger.info("创建路径====" + pat);
        }
        String path = pat + fileName;
        File localFile = new File(path);
        return localFile;
    }

    /**
     * 获取文件夹路径
     * @param czryid
     * @return 路径
     */
    public static String getPath(String czryid){
        String path = czryid + getSlash() + DateUtil.getLongCurrDateTime6() + getSlash();
        return path;
    }

    /**
     * 删除文件
     * @param filePath 文件路径（文件路径+文件名称）
     * @throws IOException 异常信息
     */
    public static void delFile(String filePath) throws IOException{
        String baseFilePath = Constants_core.HEADPORTRAITFILEPATH + getSlash() + filePath;
        File myDelFile = new File(baseFilePath);
        String logShowName = myDelFile.getName();
        myDelFile.delete();
        logger.info("删除文件" + logShowName);
    }

    /**
     * 获取展示图片路径，用于在页面上展示图片
     * @param request 请求信息
     * @return 路径
     */
    public static String getZxImgPath(HttpServletRequest request){
        /*
         * Tomcat发布时，设置的上传路径，可以通过类似
         * http://locahost:8080/项目名/图片名称
         * 进行访问图片
         */
        String path = request.getContextPath();
//        String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + TOMCATSHOWPATH + "/";
        String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/" + Constants_core.HEADPORTRAITSHOWFILEPATH + "?imgPath=";
        return imgPath;
    }

    private static String getSlash(){
        String path = Constants_core.HEADPORTRAITFILEPATH;
        String sub = path.substring(path.length() - 1, path.length());
        if ("\\".equals(sub) || "/".equals(sub)){
            return sub;
        }
        return "\\";
    }
}
