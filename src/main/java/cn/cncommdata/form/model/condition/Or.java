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
@NoArgsConstructor
@AllArgsConstructor
public class Or extends ConditionBase {
    /**
     * 条件中子条件，关系为或
     */
    private List<ConditionBase> conditions;

}
