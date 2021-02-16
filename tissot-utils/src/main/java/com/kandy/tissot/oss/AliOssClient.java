package com.kandy.tissot.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * oss工具类
 * 
 * @author shaoyn
 *
 */
public class AliOssClient extends AbstractAliOssClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AliOssClient(String bucketName, String endpoint, String accessKeyId, String accessKeySecret) {
        super(bucketName, endpoint, accessKeyId, accessKeySecret);
    }
}