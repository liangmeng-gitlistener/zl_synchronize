package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定时任务ID及描述
 */
@Getter
@AllArgsConstructor
public enum TaskEnum {
    TEST_TASK(1L, "每10s自动执行的测试定时任务。"),
    CAST_OUTPUT_TASK(2L, "每11s自动执行的铸轧产量同步定时任务。"),
    ORDER_PROGRESS_TASK(3L, "每12s自动执行的订单进度同步定时任务。"),
    COLD_ROLL_OUTPUT_TASK(4L, "每13s自动执行的冷轧产量同步定时任务。"),
    WIP_SUMMARY_TASK(5L, "每14s自动执行的在制品总量同步定时任务。"),
    ;

    private Long taskId;
    private String description;
}
