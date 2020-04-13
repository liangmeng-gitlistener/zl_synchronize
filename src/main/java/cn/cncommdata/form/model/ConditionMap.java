package cn.cncommdata.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model
 * @date:2019-04-11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionMap {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 值
     */
    private String value;

}
