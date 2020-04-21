package cn.cncommdata.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ColdRollOutput)实体类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Data
public class ColdRollOutput implements Serializable {
    private static final long serialVersionUID = 235124407648265551L;
    /**
    * 冷轧产量表id
    */
    private Long id;
    /**
    * 单月单合金产量（T）
    */
    private Integer weight;
    /**
    * 合金分类
    */
    private String alloy;
    /**
    * 月总产量
    */
    private Integer totalWeight;
    /**
    * 当前产量所属年份
    */
    private Integer year;
    /**
    * 当前产量所属月份
    */
    private Integer month;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
}