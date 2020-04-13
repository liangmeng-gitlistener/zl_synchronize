package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定时任务ID及描述
 * 2017-12-09 22:00
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    RUNNING_TASK(0, "作废状态为否"),
    STOPPED_TASK(1, "作废状态为是"),
    ;

    private Integer state;
    private String description;
}
