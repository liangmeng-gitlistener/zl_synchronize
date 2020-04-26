package cn.cncommdata.bean;

import cn.cncommdata.bean.data.XYJsonData;
import lombok.Data;

import java.util.List;

@Data
public class WIPJsonBase extends JsonBaseCommon{
    private List<XYJsonData> data;
}
