package org.com.lyz.controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import jdk.nashorn.internal.objects.Global;
import org.com.lyz.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 展示图片类
 * @author： 鲁玉震
 * @time： 2018/10/17
 */
@Controller
@RequestMapping(FileUtils.TOMCATSHOWPATH)
public class ShowImgController {

    @RequestMapping()
    public void bmpShow(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//        String imagePath = Global.getUserfilesBaseDir()+path;
        String imagePath = path;
        response.reset();
        OutputStream output = response.getOutputStream();// 得到输出流
        if (imagePath.toLowerCase().endsWith(".jpg"))// 使用编码处理文件流的情况：
        {
            response.setContentType("JPG");// 设定输出的类型
            // 得到图片的真实路径

            // 得到图片的文件流
            InputStream imageIn = new FileInputStream(new File(imagePath));
            // 得到输入的编码器，将文件流进行jpg格式编码
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
            // 得到编码后的图片对象
            BufferedImage image = decoder.decodeAsBufferedImage();
            // 得到输出的编码器
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
            encoder.encode(image);// 对图片进行输出编码
            imageIn.close();// 关闭文件流
        }
        if (imagePath.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
        {
            response.setContentType("GIF");
            ServletContext context  = RequestContextUtils.getWebApplicationContext(request).getServletContext();// 得到背景对象
            InputStream imageIn = context.getResourceAsStream(imagePath);// 文件流
            BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
            byte data[] = new byte[4096];// 缓冲字节数
            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bis.close();
            bos.flush();// 清空输出缓冲流
            bos.close();
        }
        output.close();
    }
}
