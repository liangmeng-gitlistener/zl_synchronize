package cn.cncommdata.form.model.sub;

import cn.cncommdata.form.model.DBBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 子表单
 * @author zhilong.xu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubForm extends DBBase {

    /**
     * formId 父表form_id
     */
    private Long formId;

    /**
     * formDataId 父表id
     */
    private Long formDataId;

    /**
     * 子表form_id
     */
    private Long subFormId;

    /**
     * 字表form_data_id
     */
    private Long subFormDataId;

    /**
     * 行id
     */
    private Long rowId;

    /**
     * 绑定的字段name
     */
    private String fieldName;

    /**
     * createTime/createor 在Base里面
     */
}
