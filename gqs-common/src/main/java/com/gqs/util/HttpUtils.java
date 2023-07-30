package com.gqs.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author duliwei
 * @date 2019/03/27
 */
@Slf4j
public class HttpUtils {

    private final static HttpUtils INSTANCE = new HttpUtils();

    private CloseableHttpClient httpClient = null;
    private CloseableHttpClient bigHttpClient = null;

    private PoolingHttpClientConnectionManager cm = null;

    RequestConfig requestConfig = null;
    RequestConfig bigRequestConfig = null;

    private int connTimeout = 3000;
    private int connReqTimeout = 200;
    private int socketTimeout = 500;
    private int socketbigTimeOut = 600000;

    private int maxReqNum = 500;
    private int maxPerRouteReqNum = 200;

    public static final String HEADER_COOKIE = "Cookie";
    public static final String HEADER_USER_AGNET = "User-Agent";
    public static final String HEADER_HOST = "Host";
    public static final String HEADER_REFER = "Referer";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final Integer CODE200 = 200;

    private HttpUtils() {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxReqNum);
        cm.setDefaultMaxPerRoute(maxPerRouteReqNum);

        requestConfig = RequestConfig.custom().setConnectTimeout(connTimeout)
                .setConnectionRequestTimeout(connReqTimeout)
                .setSocketTimeout(socketTimeout).build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
                .build();

        bigRequestConfig = RequestConfig.custom().setConnectTimeout(connTimeout)
                .setConnectionRequestTimeout(connReqTimeout)
                .setSocketTimeout(socketbigTimeOut).build();

        bigHttpClient = HttpClients.custom()
                .setConnectionManager(cm).setDefaultRequestConfig(bigRequestConfig)
                .build();
    }

    public static HttpUtils getInstance() {
        return INSTANCE;
    }

    private RequestConfig getRequestConfig(boolean bigTimeOut) {
        if (bigTimeOut) {
            return bigRequestConfig;
        } else {
            return requestConfig;
        }
    }

    public String httpGet(String url) {
        return this.httpGet(url, false);
    }

    public String httpGet(String url, boolean bigTimeOut) {
        return this.httpGet(url, bigTimeOut, null);
    }

    public String httpGet(String url, boolean bigTimeOut,
                          List<Entry<String, String>> header) {
        String result = null;
        long start = System.currentTimeMillis();
        try {
            HttpGet request = new HttpGet(url);
            if (header != null && !header.isEmpty()) {
                for (Entry<String, String> e : header) {
                    request.addHeader(e.getKey(), e.getValue());
                }
            }
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(request);
            } else {
                response = this.bigHttpClient.execute(request);
            }
            int retc = response.getStatusLine().getStatusCode();
            if (retc != CODE200) {
                log.warn("Faild request:[" + retc + "], " + url);
                return null;
            }
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

    public String httpPost(String url, Map<String, String> map) {
        return this.httpPost(url, map, false);
    }

    public String httpPost(String url, Map<String, String> map, boolean bigTimeOut) {
        return this.httpPost(url, map, bigTimeOut, null);
    }

    public String httpFilePost(String url, String fileUrl, String fileName, boolean bigTimeOut) {
        String result = null;
        String charset = "utf-8";
        long start = System.currentTimeMillis();
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置参数
            URL u = new URL(fileUrl);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody("file", u.openConnection().getInputStream(), ContentType.parse(u.openConnection().getContentType()), fileName).build();

            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPost);
            } else {
                response = this.bigHttpClient.execute(httpPost);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            log.info("==============记录异常请求============");
            log.warn(e.getCause() + " " + e.getMessage());
            log.info("=====================================");
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

    public String httpPost(String url, Map<String, String> body, boolean bigTimeOut,
                           List<Entry<String, String>> header) {
        String result = null;
        String charset = "utf-8";
        long start = System.currentTimeMillis();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(this.getRequestConfig(bigTimeOut));
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iterator = body.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            if (header != null && !header.isEmpty()) {
                for (Entry<String, String> e : header) {
                    httpPost.addHeader(e.getKey(), e.getValue());
                }
            }
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPost);
            } else {
                response = this.bigHttpClient.execute(httpPost);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            log.warn(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

//    public String httpJsonPost(String url, String data) {
//        return this.httpJsonPost(url, data, false);
//    }

//    public String httpJsonPost(String url, String data, boolean bigTimeOut) {
//        List<Entry<String, String>> header = new ArrayList<>();
//        Entry<String, String> jsonh = new AbstractMap.SimpleEntry<>(
//                HttpUtils.HEADER_CONTENT_TYPE, "application/json");
//        header.add(jsonh);
//        return this.httpJsonPost(url, data, bigTimeOut, header);
//    }

    public String httpJsonPost(String url, String data, boolean bigTimeOut,
                               List<Entry<String, String>> header) {
        String result = null;
        long start = System.currentTimeMillis();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(this.getRequestConfig(bigTimeOut));
            StringEntity jsonEnty = new StringEntity(data,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(jsonEnty);
            for (Entry<String, String> e : header) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPost);
            } else {
                response = this.bigHttpClient.execute(httpPost);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception e) {
            log.warn(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;

    }

    public String httpTrans(String url, HttpEntity men) {
        return this.httpTrans(url, men, false);
    }

    public String httpTrans(String url, HttpEntity men, boolean bigTimeOut) {
        String result = null;
        long start = System.currentTimeMillis();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(men);
            httpPost.setConfig(this.getRequestConfig(bigTimeOut));
            // 设置参数
            CloseableHttpResponse response = null;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPost);
            } else {
                response = this.bigHttpClient.execute(httpPost);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception e) {
            log.warn(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

    public String joinKeyValue(List<Entry<String, String>> cookie, String connector) {
        String cookieStr = "";
        for (Entry<String, String> e : cookie) {
            cookieStr += e.getKey() + "=" + e.getValue() + connector;
        }
        return cookieStr;
    }

    public String getStatus() {
        PoolStats pools = this.cm.getTotalStats();
        return pools.toString();
    }

    /**
     * 获取请求所有头部信息
     */
    public static HttpHeaders getAllHeaders(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("Authorization"));
        headers.add("X-DT-accessToken", request.getHeader("X-DT-accessToken"));
        headers.add("X-DT-brand", request.getHeader("X-DT-brand"));
        headers.add("X-DT-clientId", request.getHeader("X-DT-clientId"));
        headers.add("X-DT-lat", request.getHeader("X-DT-lat"));
        headers.add("X-DT-lng", request.getHeader("X-DT-lng"));
        headers.add("X-DT-tenant", request.getHeader("X-DT-tenant"));
        headers.add("X-DT-version", request.getHeader("X-DT-version"));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }

    public String httpPut(String url, Map<String, String> map) {
        return this.httpPut(url, map, false);
    }

    public String httpPut(String url, Map<String, String> map, boolean bigTimeOut) {
        return this.httpPut(url, map, bigTimeOut, null);
    }

    public String httpPut(String url, Map<String, String> body, boolean bigTimeOut,
                           List<Entry<String, String>> header) {
        String result = null;
        String charset = "utf-8";
        long start = System.currentTimeMillis();
        try {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(this.getRequestConfig(bigTimeOut));
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iterator = body.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPut.setEntity(entity);
            }
            if (header != null && !header.isEmpty()) {
                for (Entry<String, String> e : header) {
                    httpPut.addHeader(e.getKey(), e.getValue());
                }
            }
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPut);
            } else {
                response = this.bigHttpClient.execute(httpPut);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            log.warn(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

//    public String httpJsonPut(String url, String data) {
//        return this.httpJsonPut(url, data, false);
//    }

//    public String httpJsonPut(String url, String data, boolean bigTimeOut) {
//        List<Entry<String, String>> header = new ArrayList<>();
//        Entry<String, String> jsonh = new AbstractMap.SimpleEntry<>(
//                HttpUtils.HEADER_CONTENT_TYPE, "application/json");
//        header.add(jsonh);
//        return this.httpJsonPut(url, data, bigTimeOut, header);
//    }

    public String httpJsonPut(String url, String data, boolean bigTimeOut,
                               List<Entry<String, String>> header) {
        String result = null;
        long start = System.currentTimeMillis();
        try {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(this.getRequestConfig(bigTimeOut));
            StringEntity jsonEnty = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPut.setEntity(jsonEnty);
            for (Entry<String, String> e : header) {
                httpPut.addHeader(e.getKey(), e.getValue());
            }
            CloseableHttpResponse response;
            if (!bigTimeOut) {
                response = this.httpClient.execute(httpPut);
            } else {
                response = this.bigHttpClient.execute(httpPut);
            }
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception e) {
            log.warn(e.getCause() + " " + e.getMessage());
        }
        start = System.currentTimeMillis() - start;
        log.info("Finished :" + url + " cost:" + start);
        return result;
    }

}