package cn.cncommdata.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (FailureReporting)实体类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Data
public class FailureReporting implements Serializable {
    private static final long serialVersionUID = 597333356104567935L;
    /**
    * 月退货折线图主键
    */
    private Long id;
    /**
    * 月度成品量
    */
    private Integer finished;
    /**
    * 月度退货量
    */
    private Integer refund;
    /**
    * 当前数据所属年份
    */
    private Integer year;
    /**
    * 当前数据所属月份
    */
    private Integer month;
    /**
    * 数据录入或最后一次修改时间
    */
    private Date updateTime;
    /**
    * 冗余描述信息，用于扩展
    */
    private String description;
}