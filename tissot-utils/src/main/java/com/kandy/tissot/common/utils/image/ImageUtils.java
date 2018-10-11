package com.kandy.tissot.common.utils.image;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * <strong>ImageUtil</strong>
 * 图片处理，支持图片缩放<br>
 */
public class ImageUtils {

	/**
	 * 返回Base64编码
	 * @param filePath
	 * @return
     */
	public static String getBase64Content(String filePath) {
		if (StringUtils.isEmpty(filePath)){
			return null;
		}
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(filePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public static Boolean setBase64Content(String filePath,String base64Content){
		if (StringUtils.isEmpty(filePath)){
			return false;
		}
		if (StringUtils.isEmpty(base64Content)){
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64Content);
			for(int i=0;i<b.length;++i)
			{
				if(b[i]<0)
				{//调整异常数据
					b[i]+=256;
				}
			}
			OutputStream out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 返回图片属性
	 * @param is
	 * @return
	 */
	public static ImagePropertyVO getProperty(InputStream is) {
		BufferedImage bufferedImage = null;
		try
		{
			bufferedImage = ImageIO.read(is);
			ImagePropertyVO pv = new ImagePropertyVO();
			pv.setHeight(bufferedImage.getWidth());
			pv.setWidth(bufferedImage.getHeight());
			return pv;
		}catch(IOException e)
		{
			throw new RuntimeException();
		}
	}
}
