package com.sina.cal;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * Created by muli1 on 16/10/13.
 */
public class Request {
    public static void main(String[] args){
        try {
            String url = "http://i2.api.weibo.com/2/darwin/table/show.json?source=445670032&appkey=445670032&table=user_info_for_recommend&key="
                    + "1966695840";
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
            org.apache.http.HttpResponse httpResponse = httpClient.execute(httpGet);
            int status = httpResponse.getStatusLine().getStatusCode();
            HttpEntity respEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(respEntity, "UTF-8");
            assertEquals(200, status);
            JSONObject responseObj =  JSON.parseObject(response);
            assertEquals("445670032", responseObj.getString("appkey"));
            assertEquals("1966695840", responseObj.getJSONObject("columns").getString("uid"));
            assertEquals("{\"gender\": \"male\", \"born\": \"90s\"}", responseObj.getJSONObject("columns").getString("user_info"));
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
