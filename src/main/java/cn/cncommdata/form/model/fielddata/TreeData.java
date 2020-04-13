package cn.cncommdata.form.model.fielddata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeData extends DataBase {
    /**
     * 树类型保存的树Id
     */
    @Field("tree_id")
    private Long treeId;
    /**
     * 树类型保存的节点Id
     */
    @Field("node_id")
    private Long nodeId;
    /**
     * tree 的值
     */
    private Object value;

}
