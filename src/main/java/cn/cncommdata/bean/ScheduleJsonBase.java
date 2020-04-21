package cn.cncommdata.bean;

import cn.cncommdata.bean.data.schedule.ScheduleJsonData;
import lombok.Data;

@Data
public class ScheduleJsonBase extends JsonBaseCommon{
    private ScheduleJsonData data;
}
