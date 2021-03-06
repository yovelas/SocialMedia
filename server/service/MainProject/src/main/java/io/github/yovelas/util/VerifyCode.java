package io.github.yovelas.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

@Component
public class VerifyCode {
	private int w = 70;
	private int h = 35;
	private Random r = new Random();
	// 定义有那些字体^M
	// {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
	private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
	// // 定义有那些验证码的随机字符
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	// 生成背景色
	private Color bgColor = new Color(255, 255, 255);
	// 用于gettext 方法 获得生成的验证码文本
	private String text;

	/**
	 * 生成随机颜色
	 * 
	 * @return
	 */
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}

	/**
	 * 生成随机字体
	 * 
	 * @return
	 */
	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(5) + 24;
		return new Font(fontName, style, size);
	}

	/**
	 * 画干扰线
	 * 
	 * @param image
	 */
	private void drawLine(BufferedImage image) {
		int num = 3;
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.BLUE);
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 得到codes的长度内的随机数 并使用charAt 取得随机数位置上的codes中的字符
	 * 
	 * @return
	 */
	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	/**
	 * 创建一张图片
	 * 
	 * @return
	 */
	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return image;
	}

	/**
	 * 获取一张验证码的图片
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		StringBuilder sb = new StringBuilder();
		// 向图片中画4个字符
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			float x = i * 1.0F * w / 4;
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, h - 5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

	/**
	 * 得到验证码的文本 后面是用来和用户输入的验证码 检测用
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 定义输出的对象和输出的方向
	 * 
	 * @param image
	 * @param out
	 * @throws IOException
	 */
	public static void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}

}
