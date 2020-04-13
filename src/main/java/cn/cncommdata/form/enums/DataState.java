package cn.cncommdata.form.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.enums
 * @date:2019-04-16
 **/
public enum DataState {
    /**
     * 隐藏
     */
    HIDDEN(0, "HIDDEN"),
    /**
     * 正常
     */
    ENABLE(1, "ENABLE");
    /**
     * 描述
     */
    private String message;
    /**
     * index
     */
    private Integer index;

    /**
     * 私有构造方法
     *
     * @param message 消息
     * @param index   index
     */
    DataState(Integer index, String message) {
        this.index = index;
        this.message = message;
    }

    /**
     * 重构toString方法
     *
     * @return string
     */
    @Override
    @JsonValue
    public String toString() {
        return message;
    }


}
