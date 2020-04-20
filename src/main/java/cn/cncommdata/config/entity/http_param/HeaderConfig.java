package cn.cncommdata.config.entity.http_param;

import lombok.Data;

@Data
public class HeaderConfig {
    private String tenantId;
    private String grantId;
}
