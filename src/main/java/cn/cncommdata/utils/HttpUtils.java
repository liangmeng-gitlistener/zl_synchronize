package cn.cncommdata.utils;

import cn.cncommdata.enums.SysConstants;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.util.Map;

/**
 * 专门对接中通华报表的http工具类
 */
public class HttpUtils {

    /**
     * TODO:根据实际情况修改http请求头部信息
     * 初始化请求头
     * @return
     */
    private Map<String, String> initheaderMap (){
        Map<String, String> result = MapUtil.newHashMap();
        result.put(Header.USER_AGENT.toString(), "Hutool http");
        return result;
    }

    /**
     * GET请求普通页面
     * @param url       请求的url地址
     * @param paramMap  请求的表单内容
     * @return          页面内容的字符串
     */
    public String get(String url, Map<String, Object> paramMap){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.get(url)
                //头信息
                .headerMap(initheaderMap(), true)
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
    public String get(String url){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.get(url)
                //头信息
                .headerMap(initheaderMap(), true)
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
    public String post(String url, Map<String, Object> paramMap){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.post(url)
                //头信息
                .headerMap(initheaderMap(), true)
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
    public String post(String url, String json){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse res = HttpRequest.post(url)
                //头信息，多个头信息多次调用此方法即可
                .headerMap(initheaderMap(), true)
                .body(json)
                .timeout(SysConstants.TimeEnum.HTTP_CONNECTION_TIMEOUT.getTime())
                .execute();
        return res.body();
    }
}
