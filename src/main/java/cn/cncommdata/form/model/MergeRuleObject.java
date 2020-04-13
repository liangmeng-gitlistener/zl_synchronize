package cn.cncommdata.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 合并规则
 *
 * @author libing.niu
 * @version 1.0
 * @since 2019/11/13 12:56
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MergeRuleObject {

    /**
     * 求和规则字段
     */
    private List<String> sums;
    /**
     * 拼接字段规则
     */
    private List<String> strings;
    /**
     * 求最大值规则字段
     */
    private List<String> maxs;
    /**
     * 求最小值规则字段
     */
    private List<String> mins;
    /**
     * 求平均值规则字段
     */
    private List<String> avgs;

}
