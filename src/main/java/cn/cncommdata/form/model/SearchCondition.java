package cn.cncommdata.form.model;

import cn.cncommdata.form.model.condition.ConditionBase;
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
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    /**
     * 分类树节点Id
     */
    @Field("node_id")
    private String nodeId;
    /**
     * 搜索关键字
     */
    private String key;
    /**
     * 高级搜索条件
     */
    private List<ConditionBase> conditions;
}
