package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectData extends DataBase {
    /**
     * 选择数据，若单选时，list.size=1
     */
    @JsonRawValue
    private List<String> value;
}
