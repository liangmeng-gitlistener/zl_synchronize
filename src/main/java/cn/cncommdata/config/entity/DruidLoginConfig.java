package cn.cncommdata.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "druid-login")
@Data
public class DruidLoginConfig {
    private String userName;
    private String password;
}
