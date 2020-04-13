package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonData extends DataBase {
    /**
     * 数据的具体值的字符串
     */
    @JsonRawValue
    private String value;
}
