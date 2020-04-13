package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-18
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableData extends DataBase {

    /**
     * 数据表
     */
    @JsonRawValue
    private List<TableRow> value;
    /**
     * 当前进度的行序号
     */
    @Field("current_order")
    private Integer currentOrder = 0;
}
