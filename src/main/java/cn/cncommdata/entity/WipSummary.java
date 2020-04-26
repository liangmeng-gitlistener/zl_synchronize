package cn.cncommdata.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * (WipSummary)实体类
 *
 * @author makejava
 * @since 2020-04-23 10:18:59
 */
@Getter
@Setter
public class WipSummary implements Serializable {
    private static final long serialVersionUID = -39358244615001808L;
    /**
    * 在制品汇总表主键
    */
    private Long id;
    /**
    * 在制品名称
    */
    private String wipName;
    /**
    * 在制品卷数汇总
    */
    private Integer volume;
    /**
    * 在制品重量(T)
    */
    private Double weight;
    /**
    * 扩展字段
    */
    private String extra;
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
        } else if(!(o instanceof WipSummary)) {
            return false;
        } else {
            WipSummary object = (WipSummary) o;
            if (!StrUtil.equals(wipName, object.getWipName())) {
                return false;
            }
            if (!weight.equals(object.getWeight())) {
                return false;
            }
            if (!volume.equals(object.getVolume())) {
                return false;
            }
            if (!extra.equals(object.getExtra())) {
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
        } else if(!(o instanceof WipSummary)) {
            return false;
        } else {
            WipSummary object = (WipSummary) o;
            if (!StrUtil.equals(object.getWipName(), wipName)) {
                return false;
            }
            return true;
        }
    }
}