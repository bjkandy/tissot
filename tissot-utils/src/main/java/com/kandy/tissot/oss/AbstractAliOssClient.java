package com.kandy.tissot.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * OSS操作类
 * @Author bjkandy
 */
public abstract class AbstractAliOssClient {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected String bucketName;
	protected OSSClient instance = null;

	public AbstractAliOssClient(String bucketName, String endpoint, String accessKeyId, String accessKeySecret) {
		this.bucketName = bucketName;
		instance = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	public OSSClient getOSSClient() {
		return instance;
	}

	/**
	 * 上传文件
	 * 
	 * @param content
	 *            文件流
	 * @param objName
	 *            文件名称
	 * @return
	 */
	public boolean putObject(InputStream content, String objName) {
		try {
			if (StringUtils.isBlank(objName) || content == null || content.available() < 10) {
				return false;
			}
			OSSClient client = getOSSClient();
			ObjectMetadata meta = new ObjectMetadata();

			// 必须设置ContentLength
			meta.setContentLength(content.available());
			// 上传Object.
			client.putObject(bucketName, objName, content, meta);

			return true;
		} catch (Exception e) {
			logger.error("oss上传Object异常", e);
			return false;
		} finally {
			if (content != null) {
				try {
					content.close();
				} catch (IOException e) {
					logger.error("关闭Object流异常", e);
				}
			}
		}
	}
	
	/**
	 * 上传图片文件
	 * 
	 * @param content
	 *            文件流
	 * @param name
	 *            文件名称
	 * @return
	 */
	public boolean putImage(InputStream content, String name) {
		try {
			if (StringUtils.isBlank(name) || content == null || content.available() < 10) {
				return false;
			}
			OSSClient client = getOSSClient();
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(content.available());
			meta.setContentType(AliOssContentTypeEnum.IMAGE.getDes());
			// 上传Object.
			client.putObject(bucketName, name, content, meta);

			return true;
		} catch (Exception e) {
			logger.error("oss放图片异常", e);
			return false;
		} finally {
			if (content != null) {
				try {
					content.close();
				} catch (IOException e) {
					logger.error("关闭图片流异常", e);
				}
			}
		}
	}

	/**
	 * 根据key删除oss上的文件
	 * 
	 * @param key
	 */
	public boolean deleteObject(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		try {
			OSSClient client = getOSSClient();
			client.deleteObject(bucketName, key);
			return true;
		} catch (Exception e) {
			logger.error("删除oss信息异常" + key, e);
			return false;
		}
	}

	/**
	 * 根据在oss中的key和文件类型获得输入流<br>
	 * contentTypeEnum用来提供文件类型,oss根据这个决定获取的bucket<br>
	 * 如果获取的key不存在或者发生其它异常会返回null
	 * 
	 * @param key
	 * @param key
	 * @return
	 */
	public InputStream getInputStream(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		OSSClient client = getOSSClient();
		InputStream objectContent = null;
		try {
			OSSObject object = client.getObject(bucketName, key);
			objectContent = object.getObjectContent();
		} catch (Exception e) {
			logger.error("获取oss输入流异常key:" + key, e);
			return null;
		}
		return objectContent;
	}
}
