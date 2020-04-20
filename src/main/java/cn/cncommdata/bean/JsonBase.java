package cn.cncommdata.bean;

import cn.cncommdata.bean.data.JsonData;
import lombok.Data;

@Data
public class JsonBase {
    private Integer code;
    private String msg;
    private JsonData data;
}
