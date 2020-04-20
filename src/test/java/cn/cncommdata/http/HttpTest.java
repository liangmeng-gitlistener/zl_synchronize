package cn.cncommdata.http;

import cn.cncommdata.ZlSynchronizeApplicationTests;
import cn.cncommdata.bean.JsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.enums.SysConstants.OrderProgressParam;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
class HttpTest extends ZlSynchronizeApplicationTests {

    @Autowired
    private HttpConfig httpConfig;
    /**
     * 订单进度查询
     */
    @Test
    void OrderProgress(){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(OrderProgressParam.FORM_ID.getName(), httpConfig.getOrderProgress().getFormId());
        paramMap.put(OrderProgressParam.CONDITION.getName(), httpConfig.getOrderProgress().getCondition());
        paramMap.put(OrderProgressParam.PAGE_NUMBER.getName(), httpConfig.getOrderProgress().getPageNumber());
        paramMap.put(OrderProgressParam.PAGE_SIZE.getName(), httpConfig.getOrderProgress().getPageSize());
        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.ORDER_PROGRESS.getUrl());
        String result = HttpUtils.get(url, paramMap, httpConfig);

        JsonBase bean = JSONUtil.toBean(result, JsonBase.class);
        Map<String,Map<String,String>> map = bean.getData().getRows().get(0).getData();
        log.info(map.get("客户").get("value"));
    }
}
