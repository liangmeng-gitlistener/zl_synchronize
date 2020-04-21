package cn.cncommdata.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (GlobalReporting)实体类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Data
public class GlobalReporting implements Serializable {
    private static final long serialVersionUID = 591112321632161991L;
    /**
    * 地球模型主键
    */
    private Long id;
    /**
    * 值
    */
    private String name;
    /**
    * 名称
    */
    private String value;
    /**
    * 起点经度
    */
    private String startLongitude;
    /**
    * 起点维度
    */
    private String startLatitude;
    /**
    * 终点经度
    */
    private String endLongitude;
    /**
    * 终点维度
    */
    private String endLatitude;
    /**
    * 冗余字段，用于未知的扩展
    */
    private String description;
}