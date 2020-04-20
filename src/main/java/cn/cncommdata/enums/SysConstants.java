package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class SysConstants {

    @Getter
    @AllArgsConstructor
    public enum TimeEnum {
        HTTP_CONNECTION_TIMEOUT(20000, "http请求超时时间，毫秒."),
        ;
        private int time;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public enum HttpHeaders {
        TENANT_ID("tenant_id","1218734876489027584", "企业id"),
        GRANT_ID("grant_id","1239814084715745280", "操作者id"),
        ACCESS_TOKEN("access_token","eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp" +
                "XVCJ9.eyJhY2NvdW50X3R5cGUiOiJwaG9uZSIsImV4cCI6MTU3MzE4NDQ1NCwi" +
                "aWF0IjoxNTczMTgyNjU0LCJpZCI6MTE2MTkyMDAyODc5NTM0Mjg0MCwiaWRlbn" +
                "RpZmllciI6IjEzNzk0MzkxMzg2IiwiaXNzIjoic2NtIiwibmFtZSI6IuiDoea4" +
                "hea1qSIsInRlbmFudF9pZCI6MTE1NjQ0Nzk1Mjk5NTIyNTYwMH0.f33facd5e2" +
                "5d4415dbee0426a1796f9312f9028221d56a35e1746c9cb90defef", "超时时间，毫秒."),
        REFRESH_TOKEN("refresh_token","eyJhbGciOiJIUzI1NiIsInR5cCI6I" +
                "kpXVCJ9.eyJhY2NvdW50X3R5cGUiOiJwaG9uZSIsImV4cCI6MTU3MzE4NDQ1NC" +
                "wiaWF0IjoxNTczMTgyNjU0LCJpZCI6MTE2MTkyMDAyODc5NTM0Mjg0MCwiaWRl" +
                "bnRpZmllciI6IjEzNzk0MzkxMzg2IiwiaXNzIjoic2NtIiwibmFtZSI6IuiDoe" +
                "a4hea1qSIsInRlbmFudF9pZCI6MTE1NjQ0Nzk1Mjk5NTIyNTYwMH0.f33facd5" +
                "e25d4415dbee0426a1796f9312f9028221d56a35e1746c9cb90defef", "超时时间，毫秒."),
        ;
        private String name;
        private String defaultValue;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public enum OrderProgressParam {
        FORM_ID("form_id","1240468533985546242", "表单id"),
        CONDITION("condition","{}", "查询条件"),
        PAGE_NUMBER("page_number","1", "页码"),
        PAGE_SIZE("page_size","1000", "每页显示条数"),
        ;
        private String name;
        private String defaultValue;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public enum RoutingURL {
        ORDER_PROGRESS("/order/progress", "订单进度查询的路由地址"),
        WIP("/data/productionvolumequerysummary", "在制品情况查询汇总"),
        CAST_OUTPUT("/data/list/zhuzha","铸轧生产产量"),
        COLD_ROLL_OUTPUT("/data/list/lengzha","冷轧生产产量"),
        ;
        private String url;
        private String description;
    }
}