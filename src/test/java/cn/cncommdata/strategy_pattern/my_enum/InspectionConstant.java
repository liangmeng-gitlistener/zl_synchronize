package cn.cncommdata.strategy_pattern.my_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InspectionConstant {
    BATCH_CHANGE_WAREHOUSE(1, "批量转仓"),
    BATCH_CHANGE_SHIPPING(2, "批量转快递"),
    BATCH_REPLACE_ORDER_GOODS(3,"批量替换订单商品"),
    BATCH_DELETE_ORDER_GOODS(4,"批量删除订单商品"),
    BATCH_ADD_MEMO(5,"批量添加备注"),
    ;

    private Integer code;
    private String message;
}
