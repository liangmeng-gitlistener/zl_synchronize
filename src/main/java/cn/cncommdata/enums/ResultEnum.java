package cn.cncommdata.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 没用到，先冗余到这里后面可能会用到
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;
    private String message;
}
