package cn.cncommdata.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TCronTrigger)实体类
 *
 * @author makejava
 * @since 2020-04-10 14:09:12
 */
@Data
public class TCronTrigger implements Serializable {
    private static final long serialVersionUID = 628566639441230574L;
    /**
    * 任务id
    */
    private Long id;
    /**
    * cron表达式
    */
    private String cron;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 作废状态 0-否 1-是
    */
    private Integer isDeleted;
    /**
    * 类的全路径
    */
    private String className;
    /**
    * 任务运行开始时间
    */
    private Date startTime;
    /**
    * 任务停止运行时间
    */
    private Date stopTime;
    /**
    * 上一次任务执行的完成时间
    */
    private Date lastRunTime;
}