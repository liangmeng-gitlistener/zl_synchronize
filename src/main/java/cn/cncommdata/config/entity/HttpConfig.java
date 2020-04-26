package cn.cncommdata.config.entity;

import cn.cncommdata.config.entity.http_param.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http")
@Data
public class HttpConfig {
    private String ip;
    private String port;
    private String pageNumber;
    private String pageSize;

    private HeaderConfig header;
    private OrderProgressConfig orderProgress;
    private CastOutputConfig castOutput;
    private ColdRollOutputConfig coldRollOutput;
    private WIPParamConfig wipParam;
}
