package cn.cncommdata.bean.data.schedule.row;

import lombok.Data;

import java.util.Map;

@Data
public class JsonRows {
    private Map<String,Map<String,String>> data;
    private String status;
}
