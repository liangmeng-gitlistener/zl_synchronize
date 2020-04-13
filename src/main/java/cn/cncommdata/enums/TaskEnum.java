package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定时任务ID及描述
 * 2017-12-09 22:00
 */
@Getter
@AllArgsConstructor
public enum TaskEnum {
    TEST_TASK(1L, "每10s自动执行的测试定时任务。"),
    ;

    private Long taskId;
    private String description;
}
