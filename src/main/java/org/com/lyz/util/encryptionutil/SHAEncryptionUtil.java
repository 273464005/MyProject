package org.com.lyz.util.encryptionutil;

import org.com.lyz.util.StringUtils;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/5/22.
 */
public class SHAEncryptionUtil {

    //SHA 加密方式
    private static final String KEY_SHA = "SHA";
    //SHA-1 加密方式
    private static final String KEY_SHA_1 = "SHA-1";

    //十六进制数组
    private static final String[] hexArray = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    /**
     * 构造函数
     */
    public SHAEncryptionUtil(){

    }

    /**
     * SHA_1 加密
     * @param date 需要加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String SHA_1Encryption(String date) throws Exception{
        return encryption(date,KEY_SHA_1);
    }

    /**
     * SHA 加密
     * @param date 需要加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String SHAEncryption(String date) throws Exception{
        return encryption(date,KEY_SHA);
    }

    private static String encryption(String date,String encryptionType) throws Exception{
        //判断传入的字符串是否为空
        if (StringUtils.isEmpty(date)) {
            return "";
        }
        //选择加密方式
        MessageDigest sha = MessageDigest.getInstance(encryptionType);
        //加密
        sha.update(date.getBytes());
        //获取加密后的数组
        byte[] bytes = sha.digest();
        //返回十六进制的字符串
        return byteArrayToHexString(bytes);
    }

    /**
     * 将byte字节转化为十六进制字符
     * @param b 需要转换的字节
     * @return 十六进制字符
     */
    private static String byteToHexString(byte b){
        int rel = b;
        if(rel < 0){
            rel += 256;
        }
        int m = rel / 16;
        int n = rel % 16;
        return hexArray[m] + hexArray[n];
    }

    /**
     * 将byte数组转换为十六进制字符串
     * @param bs 需要转换的数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bs){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i < bs.length;i++){
            stringBuffer.append(byteToHexString(bs[i]));
        }
        return stringBuffer.toString();
    }
}
