package cn.cncommdata.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * (CastOutput)实体类
 *
 * @author makejava
 * @since 2020-04-23 12:38:45
 */
@Getter
@Setter
@ToString
public class CastOutput implements Serializable {
    private static final long serialVersionUID = -31831737993251118L;
    /**
    * 铸轧产量表id
    */
    private Long id;
    /**
    * 合金分类
    */
    private String alloy;
    /**
    * 单月单合金产量（T）
    */
    private Double weight;
    /**
    * 月总产量
    */
    private Double totalWeight;
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

    /**
     * 对象相等
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof CastOutput)) {
            return false;
        } else {
            CastOutput object = (CastOutput) o;
            if (!StrUtil.equals(alloy, object.getAlloy())) {
                return false;
            }
            if (!weight.equals(object.getWeight())) {
                return false;
            }
            if (!totalWeight.equals(object.getTotalWeight())) {
                return false;
            }
            if (!year.equals(object.getYear())) {
                return false;
            }
            if (!month.equals(object.getMonth())) {
                return false;
            }
            return true;
        }
    }

    /**
     * 数据库已经存在
     * @param o
     * @return
     */
    public boolean alreadyInDB(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof CastOutput)) {
            return false;
        } else {
            CastOutput object = (CastOutput) o;
            if (!StrUtil.equals(alloy, object.getAlloy())) {
                return false;
            }
            return true;
        }
    }
}