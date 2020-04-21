package cn.cncommdata.bean.data.output;

import cn.cncommdata.bean.data.JsonDataCommon;
import cn.cncommdata.bean.data.schedule.row.JsonRows;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OutputJsonData extends JsonDataCommon {
    private List<Map<String,String>> rows;
}
