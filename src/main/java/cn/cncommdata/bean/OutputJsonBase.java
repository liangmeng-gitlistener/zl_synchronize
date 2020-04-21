package cn.cncommdata.bean;

import cn.cncommdata.bean.data.output.OutputJsonData;
import lombok.Data;

@Data
public class OutputJsonBase extends JsonBaseCommon{
    private OutputJsonData data;
}
