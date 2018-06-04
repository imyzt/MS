package top.imyzt.ms.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * 图片处理
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class ImageUtil {

    private ImageUtil(){}

    public static void main(String[] args) throws IOException {
        generateWatermarkOfCompress50("D:\\abc.jpg", "D:\\def.jpg", "D:\\bdlogo.jpg");
    }

    /**
     * 使用指定图片生成指定大小的图片
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param i 比例
     * @param i1 比例
     * @throws IOException
     */
    public static void generateFixedSizeImage(String oldImg, String newImg, int i, int i1) throws IOException {
        Thumbnails.of(oldImg)
                .size(i, i1)
                .toFile(newImg);
    }

    /**
     * 压缩图片
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param quality 压缩质量 0 ~ 1
     * @throws IOException
     */
    public static void generateCompress(String oldImg, String newImg, float quality) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(new File(oldImg)));
        Thumbnails.of(oldImg)
                .size(image.getWidth(), image.getHeight())
                .outputQuality(quality)
                .toFile(newImg);
    }

    /**
     * 旋转图片
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param rotate 顺时针 0 ~ 180,逆时针 0 ~ -180
     * @throws IOException
     */
    public static void generateRotation(String oldImg, String newImg, double rotate) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(new File(oldImg)));
        Thumbnails.of(oldImg)
                .size(image.getWidth(), image.getHeight())
                .rotate(rotate)
                .toFile(newImg);
    }

    /**
     * 给图片添加水印
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param positions 水印位置
     * @param logo 水印图片
     * @param opacity 水印透明度
     * @throws IOException
     */
    public static void generateWatermark(String oldImg, String newImg, Positions positions, String logo, float opacity) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(new File(oldImg)));
        Thumbnails.of(oldImg)
                .size(image.getWidth(), image.getHeight())
                .watermark(positions, cn.hutool.core.util.ImageUtil.read(new File(logo)), opacity)
                .toFile(newImg);
    }


    /**
     * 添加水印后压缩图片
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param width 图片宽度
     * @param height 图片高度
     * @param positions 水印位置
     * @param logo 水印图片
     * @param opacity 水印透明度
     * @param quality 压缩质量 0 ~ 1
     */
    public static void generateWatermarkOfCompress(String oldImg, String newImg, int width, int height, Positions positions, String logo, float opacity, float quality) throws IOException {

        Thumbnails.of(oldImg)
                .size(width, height)
                .watermark(positions, cn.hutool.core.util.ImageUtil.read(new File(logo)), opacity)
                .outputQuality(quality)
                .toFile(newImg);
    }

    /**
     * 添加水印后压缩图片50%
     * @param oldImg 给定图片(必须存在)
     * @param newImg 新图片路径(不存在自动创建)
     * @param logo 水印图片
     * @throws IOException
     */
    public static void generateWatermarkOfCompress50(String oldImg, String newImg, String logo) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(new File(oldImg)));
        Thumbnails.of(oldImg)
                .size(image.getWidth(), image.getHeight())
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(logo)), 0.3F)
                .outputQuality(0.8)
                .toFile(newImg);
    }



}
