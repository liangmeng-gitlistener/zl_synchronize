package cn.cncommdata.bean.data;

import cn.cncommdata.bean.data.row.JsonRows;
import lombok.Data;

import java.util.List;

@Data
public class JsonData {
    //TODO
    private String formDataIds;
    //TODO
    private String ruleId;
    private Integer totalPage;
    private Integer totalCount;
    private List<JsonRows> rows;
}
