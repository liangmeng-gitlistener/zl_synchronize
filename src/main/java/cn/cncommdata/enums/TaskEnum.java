package cn.cncommdata.enums;

import lombok.Getter;

/**
 * 定时任务ID及描述
 * 2017-12-09 22:00
 */
@Getter
public enum TaskEnum {
    TEST_TASK(1L, "每10s自动执行的测试定时任务。"),
    ;

    private Long taskId;

    private String description;

    TaskEnum(Long taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }
}
