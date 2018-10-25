package com.kandy.tissot.core.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Http工具类
 */
public class HttpRequestClient {
	private static Logger logger = LoggerFactory.getLogger(HttpRequestClient.class);

	/**
	 * 连接前置机，发送请求报文，获得返回报文
	 * @param url
	 * @param data
	 * @return
	 * @throws MalformedURLException
	 */
	public static String sendRequest(String url,String data) {
		String result = "";
		try {
			if(url.contains("https://")){
				return HttpsRequestClient.sendRequest(url, data);
			}else {
				//创建连接
				logger.debug("HttpRequestClient.sendRequest url : [{}]", url);
				HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
				conn.setConnectTimeout(2000);
				conn.setReadTimeout(3000);
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Charset", "UTF8");
				OutputStream os = conn.getOutputStream();
				os.write(data.toString().getBytes("UTF8"));
				os.close();

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
				String line;
				while ((line = br.readLine()) != null) {
					result += line;
				}
				br.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sendRequestByFormData(String url, SortedMap<Object, Object> parameters) {
		String result = "";
		OutputStream outputStream = null;
		try {
			//创建连接
			HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----footfoodapplicationrequestnetwork");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Range", "bytes="+"");
			conn.setRequestMethod("POST");

			StringBuffer buffer = new StringBuffer();
			int len = 0;

			Set es = parameters.entrySet();
			Iterator it = es.iterator();

			while(it.hasNext()) {
				Map.Entry sign = (Map.Entry)it.next();
				String k = (String)sign.getKey();
				Object v = sign.getValue();
				if ("file".equals(k)){
					//文件
					try{
						String srcPath = String.valueOf(v);
						FileInputStream fis = new FileInputStream(srcPath);
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"" + k + "\"; filename=\"" + srcPath.substring(srcPath.lastIndexOf("\\") + 1) + "\"" + "\r\n\r\n");
						buffer.append("Content-Type: application/octet-stream" + "\r\n");
						buffer.append("Content-Transfer-Encoding: binary" + "\r\n");
						byte[] buff = new byte[8192]; // 8k
						int count = 0;
						// 读取文件
						while ((count = fis.read(buff)) != -1){
							buffer.append(buffer, 0, count);
						}
						buffer.append("\r\n\r\n");
						fis.close();
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}else{
					if(null != v && !"".equals(v)) {
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"");
						buffer.append(k);
						buffer.append("\"\r\n\r\n");
						buffer.append(v);
						buffer.append("\r\n");
					}
				}
			}
			if(parameters != null)
				buffer.append("------footfoodapplicationrequestnetwork--\r\n");

			outputStream = conn.getOutputStream();
			outputStream.write(buffer.toString().getBytes());

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public static InputStream sendRequestByFormDataReturnIO(String url, SortedMap<Object, Object> parameters) {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			//创建连接
			HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----footfoodapplicationrequestnetwork");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Range", "bytes="+"");
			conn.setRequestMethod("POST");

			StringBuffer buffer = new StringBuffer();
			int len = 0;

			Set es = parameters.entrySet();
			Iterator it = es.iterator();

			while(it.hasNext()) {
				Map.Entry sign = (Map.Entry)it.next();
				String k = (String)sign.getKey();
				Object v = sign.getValue();
				if ("file".equals(k)){
					//文件
					try{
						String srcPath = String.valueOf(v);
						FileInputStream fis = new FileInputStream(srcPath);
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"" + k + "\"; filename=\"" + srcPath.substring(srcPath.lastIndexOf("\\") + 1) + "\"" + "\r\n\r\n");
						buffer.append("Content-Type: application/octet-stream" + "\r\n");
						buffer.append("Content-Transfer-Encoding: binary" + "\r\n");
						byte[] buff = new byte[8192]; // 8k
						int count = 0;
						// 读取文件
						while ((count = fis.read(buff)) != -1){
							buffer.append(buffer, 0, count);
						}
						buffer.append("\r\n\r\n");
						fis.close();
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}else{
					if(null != v && !"".equals(v)) {
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"");
						buffer.append(k);
						buffer.append("\"\r\n\r\n");
						buffer.append(v);
						buffer.append("\r\n");
					}
				}
			}
			if(parameters != null)
				buffer.append("------footfoodapplicationrequestnetwork--\r\n");
			logger.info(buffer.toString());
			outputStream = conn.getOutputStream();
			outputStream.write(buffer.toString().getBytes());
			inputStream = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	public static String sendRequest(String url, SortedMap<Object, Object> parameters,String fileName) {
		String result = "";
		OutputStream outputStream = null;
		InputStream inputStream = null;
		FileOutputStream outSTr = null;
		try {
			//创建连接
			HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----footfoodapplicationrequestnetwork");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Range", "bytes="+"");
			conn.setRequestMethod("POST");

			StringBuffer buffer = new StringBuffer();

			Set es = parameters.entrySet();
			Iterator it = es.iterator();

			while(it.hasNext()) {
				Map.Entry sign = (Map.Entry)it.next();
				String k = (String)sign.getKey();
				Object v = sign.getValue();
				if ("file".equals(k)){
					//文件
					try{
						String srcPath = String.valueOf(v);
						FileInputStream fis = new FileInputStream(srcPath);
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"" + k + "\"; filename=\"" + srcPath.substring(srcPath.lastIndexOf("\\") + 1) + "\"" + "\r\n\r\n");
						buffer.append("Content-Type: application/octet-stream" + "\r\n");
						buffer.append("Content-Transfer-Encoding: binary" + "\r\n");
						byte[] buff = new byte[8192]; // 8k
						int count = 0;
						// 读取文件
						while ((count = fis.read(buff)) != -1){
							buffer.append(buffer, 0, count);
						}
						buffer.append("\r\n\r\n");
						fis.close();
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}else{
					if(null != v && !"".equals(v)) {
						buffer.append("------footfoodapplicationrequestnetwork\r\n");
						buffer.append("Content-Disposition: form-data; name=\"");
						buffer.append(k);
						buffer.append("\"\r\n\r\n");
						buffer.append(v);
						buffer.append("\r\n");
					}
				}
			}
			if(parameters != null)
				buffer.append("------footfoodapplicationrequestnetwork--\r\n");

			outputStream = conn.getOutputStream();
			outputStream.write(buffer.toString().getBytes());
			int responseCode = conn.getResponseCode();
			if(200 == responseCode){
				inputStream = conn.getInputStream();
				File file = new File(fileName);
				if(file.exists()){
					file.delete();
				}
				outSTr = new FileOutputStream(file);
				int len = 0;
				while((len = inputStream.read()) != -1){
					outSTr.write(len);
				}
			}else{
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF8"));
				String line;
				while ((line = br.readLine()) != null) {
					result += line;
				}
				br.close();
			}
		return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != inputStream) {
					inputStream.close();
				}
				if(null != outSTr) {
					outSTr.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}



}