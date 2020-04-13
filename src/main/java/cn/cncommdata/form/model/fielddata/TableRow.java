package cn.cncommdata.form.model.fielddata;

import cn.cncommdata.form.enums.DataState;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-18
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRow  implements Serializable {
    /**
     * 数据Id
     */
    private Long id;
    /**
     * 该数据为复制时，保存复制源的id
     */
    @Field("copy_source_id")
    private Long copySourceId;
    /**
     * 该数据为合并时，记录合并时数据来源Id列表
     */
    @Field("merge_sources")
    private List<Long> mergeSources;
    /**
     * 数据状态
     */
    private DataState status;
    /**
     * 一行数据
     */
    @JsonRawValue
    private Map<String, DataBase> value;

    /**
     * 选中行的rule_ids
     */
    @Field("rule_ids")
    private List<Long> ruleIds;
    /**
     * 当前row的排序编号
     */
    private Integer order;
    /**
     * 当前行的排序开关
     */
    private Boolean lock  = false;

}
