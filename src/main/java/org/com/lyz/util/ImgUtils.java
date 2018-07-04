package org.com.lyz.util;

import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.constant.Constant_htgl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 图片工具类
 * @author： 鲁玉震
 * @time： 2018/7/3
 */
public class ImgUtils {

    /**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file){
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int getImgShowHeight(GG_IMGS gg_imgs){
        double imgHeight = ConvertUtils.createDouble(gg_imgs.getHeight());
        double imgWidth = ConvertUtils.createDouble(gg_imgs.getWidth());
        int width = Constant_htgl.GG_IMGS_WIDTH_SHOW;
        String hg = ConvertUtils.createString(width * (imgHeight / imgWidth));
        int height = ConvertUtils.toInt(hg.substring(0, hg.lastIndexOf(".")));
        return height;
    }

}
