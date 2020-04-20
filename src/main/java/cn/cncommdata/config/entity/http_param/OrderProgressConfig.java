package cn.cncommdata.config.entity.http_param;

import lombok.Data;

@Data
public class OrderProgressConfig {
    private String formId;
    private String condition;
    private String pageNumber;
    private String pageSize;
}
