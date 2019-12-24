package com.gqs.example;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.EnumMap;

/**
 * 描述:
 *
 * @author 郭乔森
 * @create 2019-07-19 11:37
 */
public class ReadQrCode {

    public static void main(String[] args) {
        String filePath = "D:\\work\\ideaProjects\\gqs-parent\\gqs-web\\src\\main\\resources\\img1\\微信图片_20190719121501.jpg";
//        String filePath = "D:\\work\\ideaProjects\\gqs-parent\\gqs-web\\src\\main\\resources\\img1\\微信图片_20190719120917.jpg";
//        String filePath = "D:\\work\\ideaProjects\\gqs-parent\\gqs-web\\src\\main\\resources\\img1\\1.jpg";
//        String filePath = "D:\\work\\ideaProjects\\gqs-parent\\gqs-web\\src\\main\\resources\\img1\\1563509165(1).jpg";
//        String filePath = "D:\\work\\ideaProjects\\gqs-parent\\gqs-web\\src\\main\\resources\\img1\\index.png";
        String s = decodeQR(filePath);
        System.out.println(s);
    }

    /**
     * 解析二维码图片
     *
     * @param filePath 图片路径
     */
    public static String decodeQR(String filePath) {
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "二维码图片不存在!";
        }
        String content = "";
        EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8"); // 指定编码方式,防止中文乱码
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);// 失败
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
