package com.kandy.tissot.core.utils.http;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Https工具类
 */
public class HttpsRequestClient {
    /**
     * SSL工具类
     */
    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 连接前置机，发送请求报文，获得返回报文
     *
     * @param url       请求地址
     * @param data      请求数据
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendRequest(String url, String data) {
        String result = "";
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());

            HttpsURLConnection conn = (HttpsURLConnection) (new URL(url)).openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Charset", "UTF8");
            conn.connect();

            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes("UTF8"));
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            br.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 连接前置机，发送请求报文，获得返回报文
     *
     * @param url       请求地址
     * @param data      请求数据
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendRequest(String url, String data,String charSet) {
        String result = "";
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());

            HttpsURLConnection conn = (HttpsURLConnection) (new URL(url)).openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(3000);
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes(charSet));
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            br.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 连接前置机，发送请求报文，获得返回报文
     *
     * @param url       请求地址
     * @param data      请求数据
     * @param charSet 编码格式
     * @param fileName 文件地址
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static Boolean sendRequest(String url, String data,String charSet,String fileName) {
        InputStream inputStream = null;
        FileOutputStream outSTr = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());

            HttpsURLConnection conn = (HttpsURLConnection) (new URL(url)).openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes(charSet));
            os.close();

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
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (Exception e){
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
        return false;
    }
}
