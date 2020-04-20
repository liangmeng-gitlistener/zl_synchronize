package cn.cncommdata.utils;

import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.enums.SysConstants;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.cncommdata.enums.SysConstants.HttpHeaders;

import java.util.Map;

/**
 * 专门对接中通华报表的http工具类
 */
public class HttpUtils {

    /**
     * 初始化请求头
     * @param httpConfig    配置类
     * @return
     */
    private static Map<String, String> initHeaderMap(HttpConfig httpConfig){
        Map<String, String> result = MapUtil.newHashMap();
        result.put(HttpHeaders.TENANT_ID.getName(), httpConfig.getHeader().getTenantId());
        result.put(HttpHeaders.GRANT_ID.getName(), httpConfig.getHeader().getGrantId());
        result.put(HttpHeaders.ACCESS_TOKEN.getName(), HttpHeaders.ACCESS_TOKEN.getDefaultValue());
        result.put(HttpHeaders.REFRESH_TOKEN.getName(), HttpHeaders.REFRESH_TOKEN.getDefaultValue());
        return result;
    }

    /**
     * GET请求普通页面
     * @param url       请求的url地址
     * @param paramMap  请求的表单内容
     * @return          页面内容的字符串
     */
    public static String get(String url, Map<String, Object> paramMap, HttpConfig httpConfig){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.get(url)
                //头信息
                .headerMap(initHeaderMap(httpConfig), true)
                .form(paramMap)
                .timeout(SysConstants.TimeEnum.HTTP_CONNECTION_TIMEOUT.getTime())
                .execute();
        return res.body();
    }

    /**
     * GET请求普通页面
     * @param url       请求的url地址
     * @return          页面内容的字符串
     */
    public static String get(String url, HttpConfig httpConfig){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.get(url)
                //头信息
                .headerMap(initHeaderMap(httpConfig), true)
                .timeout(SysConstants.TimeEnum.HTTP_CONNECTION_TIMEOUT.getTime())
                .execute();
        return res.body();
    }

    /**
     * POST请求普通页面
     * @param url       请求的url地址
     * @param paramMap  请求的表单内容
     * @return          页面内容的字符串
     */
    public static String post(String url, Map<String, Object> paramMap, HttpConfig httpConfig){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.post(url)
                //头信息
                .headerMap(initHeaderMap(httpConfig), true)
                .form(paramMap)
                .timeout(SysConstants.TimeEnum.HTTP_CONNECTION_TIMEOUT.getTime())
                .execute();
        return res.body();
    }

    /**
     * POST请求Restful请求
     * @param url       请求的url地址
     * @param json      指定请求内容，比如rest请求指定JSON请求体
     * @return          页面内容的字符串
     */
    public static String post(String url, String json, HttpConfig httpConfig){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.post(url)
                //头信息，多个头信息多次调用此方法即可
                .headerMap(initHeaderMap(httpConfig), true)
                .body(json)
                .timeout(SysConstants.TimeEnum.HTTP_CONNECTION_TIMEOUT.getTime())
                .execute();
        return res.body();
    }

    /**
     * 初始化url请求地址
     * @param httpConfig        配置类
     * @param routingAddress    路由地址后缀（举例:/order/progress）
     * @return
     */
    public static String initURL (HttpConfig httpConfig, String routingAddress){
        return StrUtil.format("http://{}:{}/zlreport{}",
                httpConfig.getIp(), httpConfig.getPort(), routingAddress);
    }
}
