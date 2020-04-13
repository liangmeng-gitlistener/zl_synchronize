package cn.cncommdata.form.enums;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.tree.enums
 * @date:2019-04-08
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormDataState {

    /**
     * 草稿
     */
    DRAFT(1, "DRAFT"),
    /**
     * 修改中
     */
    EDITING(2, "EDITING"),
    /**
     * 新建审核中
     */
    APPROVAL_CREATE(3, "APPROVAL_CREATE"),
    /**
     * 审核中
     */
    APPROVAL_EDIT(4, "APPROVAL_EDIT"),
    /**
     * 审核锁定
     */
    APPROVAL_LOCK(5, "APPROVAL_LOCK"),
    /**
     * 删除审核中
     */
    APPROVAL_DELETE(6, "APPROVAL_DELETE"),
    /**
     * 正常使用中
     */
    ENABLE(7, "ENABLE"),
    /**
     * 已删除
     */
    DELETED(8, "DELETED"),
    /**
     * 版本未激活
     */
    INACTIVE(9, "INACTIVE");

    /**
     * 描述
     */
    private String message;
    /**
     * index
     */
    private Integer index;

    /**
     * getMessage
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * setMessage
     * @param message 信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * getIndex
     * @return Integer
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * setIndex
     * @param index 索引
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * valueOfMessage
     * @param message 信息
     * @return FormDataState
     */
    public static FormDataState valueOfMessage(String message) {
        FormDataState result = null;
        FormDataState[] arr = values();
        int len = arr.length;

        for (int i = 0; i < len; ++i) {
            FormDataState formDataState = arr[i];
            if (formDataState.getMessage().equals(message)) {
                result = formDataState;
                break;
            }
        }
        return result;
    }

    /**
     * valueOfIndex
     * @param index 索引
     * @return FormDataState
     */
    public static FormDataState valueOfIndex(int index) {
        FormDataState result = null;
        FormDataState[] arr = values();
        int len = arr.length;

        for (int i = 0; i < len; ++i) {
            FormDataState formDataState = arr[i];
            if (formDataState.getIndex() == index) {
                result = formDataState;
                break;
            }
        }
        return result;
    }

    /**
     * valueOfObject
     * @param obj JSON对象
     * @return FormDataState
     */
    @JsonCreator
    public static FormDataState valueOfObject(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        Integer index = obj.getInteger("index");
        String name = null;
        String message = null;
        if (index == null) {
            name = obj.getString("name");
            if (name == null || "".equals(name)) {
                message = obj.getString("message");
            }
        }
        if (index != null) {
            return valueOfIndex(index);
        }
        if (name == null || "".equals(name)) {
            return valueOf(name);
        }
        if (name == null || "".equals(message)) {
            return valueOfMessage(message);
        }
        return null;
    }



    /**
     * 私有构造方法
     *
     * @param message 消息
     * @param index   index
     */
    FormDataState(Integer index, String message) {
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

//    /**
//     * 获取本身
//     * @param formDataState 信息
//     * @return Integer
//     */
//    public Integer getByMessage(FormDataState formDataState) {
//        return formDataState.index;
//    }
}
