package cn.cncommdata.bean.data.schedule;

import cn.cncommdata.bean.data.JsonDataCommon;
import cn.cncommdata.bean.data.schedule.row.JsonRows;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleJsonData extends JsonDataCommon {
    private List<JsonRows> rows;
}
