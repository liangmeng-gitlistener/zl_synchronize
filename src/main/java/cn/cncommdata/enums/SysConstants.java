package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class SysConstants {

    @Getter
    @AllArgsConstructor
    public enum TimeEnum {
        HTTP_CONNECTION_TIMEOUT(20000, "超时时间，毫秒."),
        ;
        private int time;
        private String description;
    }
}
