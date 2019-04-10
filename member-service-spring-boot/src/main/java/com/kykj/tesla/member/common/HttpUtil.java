package com.kykj.tesla.member.common;

import com.kykj.tesla.platform.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class HttpUtil {

    /**
     * 向服务端发送 get请求
     */
    public static String httpGET(String url) {
        String responseText = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText =  EntityUtils.toString(entity);
            }
            log.info("第三方服务端相应结果 ：" + responseText);
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("连接第三方服务端异常 ：" + e.getMessage());
            throw new BaseException("10007", "连接第三方服务端失败 "+e.getMessage());
        } finally {
            try {
                // 关闭连接,释放资源
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /**
     * 向微信服务端发送 post请求
     * @param data
     * @param url
     * @return
     */
    public static String httpPost(String data, String url){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/raw");
        StringEntity formEntity = new StringEntity(data, "utf-8");
        // 发送Text格式的数据请求
        formEntity.setContentType("Json");
        httpPost.setEntity(formEntity);
        CloseableHttpClient httpCient = HttpClients.createDefault();
        HttpResponse httpresponse = null;
        try {
            httpresponse = httpCient.execute(httpPost);
            HttpEntity httpEntity = httpresponse.getEntity();
            String responseText = EntityUtils.toString(httpEntity, "utf-8");
            log.info("第三方服务端相应结果 ：" + responseText);
            return responseText;
        } catch (Exception e) {
            log.error("连接第三方服务端失败 ：" + e.getMessage());
            throw new BaseException("10007", "连接第三方服务端失败 "+e.getMessage());
        } finally {
            try {
                httpCient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
