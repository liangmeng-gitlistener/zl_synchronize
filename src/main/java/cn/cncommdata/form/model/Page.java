package cn.cncommdata.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @param <T> 实体泛型
 * @description: 分页实体类
 * @author: zhutianpeng
 * @createtime: 2019/04/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 每页显示集合
     */
    private List<T> rows;

}
