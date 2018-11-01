package org.com.lyz.util;

import org.com.lyz.base.model.po.GG_IMGS;
import org.com.lyz.constant.Constants_htgl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

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

    /**
     * 展示图片缩略图高度
     * @param gg_imgs 图片信息
     * @return 图片高度
     */
    public static int getImgShowHeight(GG_IMGS gg_imgs){
        int width = Constants_htgl.GG_IMGS_WIDTH_SHOW;
        return getImgShowHeight(gg_imgs,width);
    }


    /**
     * 展示图片高度
     * @param gg_imgs 图片信息
     * @param width 展示宽度
     * @return 图片高度
     */
    public static int getImgShowHeight(GG_IMGS gg_imgs,int width){
        double imgHeight = ConvertUtils.createDouble(gg_imgs.getHeight());
        double imgWidth = ConvertUtils.createDouble(gg_imgs.getWidth());
        String hg = ConvertUtils.createString(width * (imgHeight / imgWidth));
        int height = ConvertUtils.toInt(hg.substring(0, hg.lastIndexOf(".")));
        return height;
    }

    /**
     * 生成验证码
     * @return 生成的验证码和图片
     */
    public static Object[] getValidationCode(){
        Object obj[] = new Object[2];
        //区分大小写
        //char[] codeArraysCaseSensitive = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' ,'J','K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y','3', '4', '5', '6', '7', '8', '9','a','b','c','d','e','f','g','h', 'j','k','m','n','r','s','t','u','v','w','x','y'};
        //验证码不区分大小写
        char[] codeArraysNotCaseSensitive = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' ,'J','K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y','3', '4', '5', '6', '7', '8', '9'};
        //int codeCaseSensitiveLength = codeArraysCaseSensitive.length;
        int codeNotCaseSensitiveLength = codeArraysNotCaseSensitive.length;
        int width = 120, height = 30;
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
//        g.setColor(getRandomColor(180, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
//        g.setColor(getRandomColor(120, 180));

        // 用户保存最后随机生成的验证码
        StringBuffer validationCode = new StringBuffer();
        //验证码随机字体
        String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };
        // 随机生成4个验证码
        for (int i = 0; i < 4; i++) {
            // 随机设置当前验证码的字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
            // 随机获得当前验证码的字符
            char codeChar = codeArraysNotCaseSensitive[random.nextInt(codeNotCaseSensitiveLength)];
            validationCode.append(codeChar);
            // 随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10, 100));
            // 在图形上输出验证码字符，x和y都是随机生成的
            g.drawString(String.valueOf(codeChar), 22 * i + random.nextInt(10),height - random.nextInt(6));
        }
        g.dispose();

        obj[0] = validationCode;
        obj[1] = image;
        return obj;
    }

    /**
     * 获取随机颜色
     * @param minColor 最小颜色范围
     * @param maxColor 最大颜色范围
     * @return 随机颜色
     */
    private static Color getRandomColor(int minColor, int maxColor) {
        Random random = new Random();
        if(minColor > 255){
            minColor = 255;
        }
        if(maxColor > 255){
            maxColor = 255;
        }
        //获得r的随机颜色值
        int red = minColor+random.nextInt(maxColor-minColor);
        int green = minColor + random.nextInt(maxColor-minColor);
        int blue = minColor + random.nextInt(maxColor-minColor);
        return new Color(red,green,blue);
    }

}
