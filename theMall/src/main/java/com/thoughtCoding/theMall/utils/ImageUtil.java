package com.thoughtCoding.theMall.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class ImageUtil {

//    public static void main(String[] args) {
//        String img = imageToString("/home/benjamin/IdeaProjects/Benjamin/theMall/src/main/java/com.thoughtCoding.theMall.utils/lion.jpg");
//        System.out.println(img);
//        stringToImage(img, "/home/benjamin/IdeaProjects/Benjamin/theMall/src/main/java/com.thoughtCoding.theMall.utils/lion1.jpg");
//    }
    /**
     * 字符串转图片
     *
     * @param imgStr   --->图片字符串
     * @param filename --->图片名
     * @return
     */
    public static boolean stringToImage(String imgStr, String filename) {

        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(filename);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 图片转字符串
     *
     * @param filePath --->文件路径
     * @return
     */
    public static String imageToString(String filePath) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
