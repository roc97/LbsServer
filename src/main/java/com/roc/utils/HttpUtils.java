package com.roc.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author roc
 */
public class HttpUtils {
    /**
     * Http协议GET请求
     * @param url 请求地址
     * @return
     * @throws Exception
     */
     public static String httpGet(String url) throws Exception{
         //初始化HttpClient
         CloseableHttpClient httpClient = HttpClients.createDefault();
         //创建HttpGet
         HttpGet httpGet = new HttpGet(url);
         //发起请求，获取response对象
         CloseableHttpResponse response = httpClient.execute(httpGet);
         //获取请求状态码
         //response.getStatusLine().getStatusCode();
         //获取返回数据实体对象
         HttpEntity entity = response.getEntity();
         //转为字符串
         String result = EntityUtils.toString(entity,"UTF-8");
         return result;

     }

    /**
     * Http协议Post请求
     * @param url 请求地址
     * @param json 参数
     * @return
     * @throws Exception
     */
     public static String httpPost (String url,String json) throws Exception{
         //初始HttpClient
         CloseableHttpClient httpClient = HttpClients.createDefault();
         //创建Post对象
         HttpPost httpPost = new HttpPost(url);
         //设置Content-Type
         httpPost.setHeader("Content-Type","application/json");
         //写入JSON数据
         httpPost.setEntity(new StringEntity(json));
         //发起请求，获取response对象
         CloseableHttpResponse response = httpClient.execute(httpPost);
         //获取请求码
         //response.getStatusLine().getStatusCode();
         //获取返回数据实体对象
         HttpEntity entity = response.getEntity();
         //转为字符串
         String result = EntityUtils.toString(entity,"UTF-8");
         return result;

     }

    /**
     * Https协议GET请求
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static String httpsGet(String url) throws Exception {
        CloseableHttpClient hp = createSSLClientDefault();
        HttpGet hg = new HttpGet(url);
        CloseableHttpResponse response = hp.execute(hg);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"UTF-8");
        hp.close();
        return content;
    }

    /**
     * Https协议post请求
     * @param url 请求地址
     * @param json 参数
     * @return
     * @throws Exception
     */
    public static String httpsPost(String url, String json) throws Exception {

         CloseableHttpClient hp = createSSLClientDefault();
         HttpPost httpPost = new HttpPost(url);
         httpPost.setHeader("Content-Type","application/json");
         httpPost.setEntity(new StringEntity(json));
         CloseableHttpResponse response = hp.execute(httpPost);
         HttpEntity entity = response.getEntity();
         String content = EntityUtils.toString(entity,"UTF-8");
         hp.close();
         return content;
    }


    public static CloseableHttpClient createSSLClientDefault() throws Exception {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){
            //信任所有
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
}
