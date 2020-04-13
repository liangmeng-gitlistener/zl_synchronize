package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteData extends DataBase {
    /**
     * 数据的具体值的字符串（name字段的值，与定义的data属性无关）
     */
    @JsonRawValue
    private String value;
    /**
     * 引用的数据源id
     */
    @Field("quote_source_id")
    private Long quoteSourceId;
    /**
     * 引用的定义的id
     */
    @Field("quote_form_id")
    private Long quoteFormId;

    /**
     * 引用的数据源二级id
     */
    @Field("quote_source_row_id")
    private Long quoteSourceRowId;
}
