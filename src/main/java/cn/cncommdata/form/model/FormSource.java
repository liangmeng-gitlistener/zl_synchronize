package cn.cncommdata.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model
 * @date:2019-04-11
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSource {
    /**
     * 来源formId
     */
    @Field("form_id")
    private Long formId;
    /**
     * 来源form的dataId
     */
    @Field("data_id")
    private Long dataId;
    /**
     * 来源form中list的rowId
     */
    @Field("row_ids")
    private List<Long> rowIds;

    /**
     * 规格id
     */
    @Field("rule_id")
    private Long ruleId;




}
