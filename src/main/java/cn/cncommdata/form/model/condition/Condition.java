package cn.cncommdata.form.model.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.metadata.model.condition
 * @date:2019-04-22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition extends ConditionBase {
    /**
     * 表单id
     */
    private Long formId;
    /**
     * 字段名
     */
    private String field;
    /**
     * 操作符，>,<,!=,=,in,contains等
     */
    private String operate;
    /**
     * 值
     */
    private Object value;
    /**
     *筛选条件
     */
    private List<ConditionBase> source;
}
