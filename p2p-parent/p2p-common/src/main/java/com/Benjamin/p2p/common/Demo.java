package com.Benjamin.p2p.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.LockSupport;

/**
 * ClassName:Demo
 * Package:com.Benjamin.p2p.common
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-11-3 上午10:49
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            String url = "http://49.235.228.156:8989/theMall/captureFace";
            String para = "{\"action\": \"visitor\",\"vip_name\": \"w\",\"vip_num\": 3,\"face_url\": \"http://benjaminlee.cn:8989/hello/images/20181115213421.jpg\",\"frame_url\": \"http://benjaminlee.cn:8989/hello/images/20181115213421.jpg\",\"timestamp\": 1564730279999}";
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("msg",para);
            doPost(url,paraMap);
            System.out.println(new Date() + " log: ");
            System.out.println(para);
            System.out.println();
            LockSupport.parkUntil(new Date().getTime() + (10*1000));
        }
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //创建httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            //配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//设置连接请求超时时间
                    .setSocketTimeout(60000)//设置读取数据连接超时时间
                    .build();
            //为httpPost实例设置配置
            httpPost.setConfig(requestConfig);
            //封装post请求参数
            if (null != paramMap && paramMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                //通过map集成entrySet方法获取entity
                Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                //循环遍历，获取迭代器
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> mapEntry = iterator.next();
                    nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
                }
                //为httpPost设置封装好的请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            //执行post请求得到返回对象
            response = httpClient.execute(httpPost);
            //通过返回对象获取数据
            HttpEntity entity = response.getEntity();
            //将返回的数据转换为字符串
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
