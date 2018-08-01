package org.com.lyz.util;

import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.base.model.po.IMG_LOG;
import org.com.lyz.service.htgl.CzryService;
import org.com.lyz.service.htgl.ImgLogService;
import org.com.lyz.service.htgl.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;
import java.util.Random;

/**
 * 文件上传工具类
 * @author： 鲁玉震
 * @time： 2018/7/3
 */
public class FileUtils {

    /**
     * 获取文件上传路径
     * @param request 请求信息
     * @return 路径
     */
    public static String getFIlePath(HttpServletRequest request){
        String xmPath = request.getSession().getServletContext().getRealPath("/");
        String pat=xmPath.substring(0,xmPath.length()-4)+"uploadImgs\\";//获取文件保存路径
        return pat;
    }

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
     * @param request 请求信息
     * @param fileName 图片名称
     * @return 上传结果
     */
    public static File upload(HttpServletRequest request,String fileName){
        String pat = getFIlePath(request);
        File fileDir=new File(pat);
        if (!fileDir.exists()) { //如果不存在 则创建
            fileDir.mkdirs();
        }
        String path = pat + fileName;
        File localFile = new File(path);
        return localFile;
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
        String tomcatUploadPath = "/uploadImg/";
        String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + tomcatUploadPath;
        return imgPath;
    }
}
