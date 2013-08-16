/**
 */
package com.vdc.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;

/**
 * 图片处理工具类
 * @author jack
 * @date 2013-5-23
 */
public class ImageUtil {
	/**
	 * 几种常见的图片格式
	 */
	public static String IMAGE_TYPE_GIF = "GIF";// 图形交换格式
	public static String IMAGE_TYPE_JPG = "JPG";// 联合照片专家组
	public static String IMAGE_TYPE_JPEG = "JPEG";// 联合照片专家组
	public static String IMAGE_TYPE_BMP = "BMP";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
	public static String IMAGE_TYPE_PNG = "PNG";// 可移植网络图形
	public static String IMAGE_TYPE_PSD = "PSD";// Photoshop的专用格式Photoshop

	/**
	 * 重新画图片安装固定width,height（这种方式有点拉升和缩放的感觉，不建议使用）
	 * 
	 * @param sourceFile
	 * @param destFile
	 * @param destWidth
	 * @param destHeight
	 * @author jack
	 * @throws InterruptedException
	 */
	public static Boolean drawImageFix(File sourceFile, File destFile, int destWidth, int destHeight) throws InterruptedException {
		String imageType = sourceFile.getName().substring(sourceFile.getName().lastIndexOf(".") + 1).toUpperCase();
		try {
			BufferedImage src = ImageIO.read(sourceFile); // 读入文件

			// 获取一个宽、长的图像实例
			Image image = src.getScaledInstance(destWidth, destHeight, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_3BYTE_BGR); // 缩放图像

			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(tag, imageType, bos);// 输出到bos

			FileOutputStream out = new FileOutputStream(destFile);
			out.write(bos.toByteArray()); // 写文件
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Boolean.TRUE;
	}

	/**
	 * 不失真的自动按比例缩放，(会按照长高的比例取个最合适的)
	 * 
	 * @param sourceFile
	 * @param destFile
	 * @param destWidth
	 * @param destHeight
	 * @param usedBlank
	 *            是否使用补白效果；
	 * @return
	 * @throws InterruptedException
	 * @author jack
	 */
	public static Boolean drawImageScale(File sourceFile, File destFile, int destWidth, int destHeight) throws InterruptedException {
		String imageType = sourceFile.getName().substring(sourceFile.getName().lastIndexOf(".") + 1).toUpperCase();
		try {
			BufferedImage srcBufferedImage = ImageIO.read(sourceFile); // 读入文件
			Image image = srcBufferedImage.getScaledInstance(destWidth, destHeight, Image.SCALE_DEFAULT);

			double ratio = 0;
			// 计算比例碰到问题了可以优化这里
			if ((srcBufferedImage.getHeight() > destHeight) || (srcBufferedImage.getWidth() > destWidth)) {
				if (srcBufferedImage.getHeight() > srcBufferedImage.getWidth()) {
					ratio = (new Integer(destHeight)).doubleValue() / srcBufferedImage.getHeight();
				} else {
					ratio = (new Integer(destWidth)).doubleValue() / srcBufferedImage.getWidth();
				}

				// 安装比例压缩过滤
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				image = op.filter(srcBufferedImage, null);
			}

			ImageIO.write((BufferedImage) image, imageType, destFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Boolean.TRUE;
	}

	/**
	 * @param args
	 * @author jack
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		String fileName = new ClassPathResource("test.jpg").getFile().getPath();
		ImageUtil.drawImageFix(new ClassPathResource("test.jpg").getFile(), new File(fileName.substring(0, fileName.indexOf(".")) + "1111.jpg"), 250, 250);
	}
}
