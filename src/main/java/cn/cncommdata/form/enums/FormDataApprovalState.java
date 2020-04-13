package cn.cncommdata.form.enums;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.tree.enums
 * @date:2019-04-02
 **/
public enum FormDataApprovalState {
    /**
     * 未提交
     */
    DRAFT("DRAFT"),
    /**
     * 审核中
     */
    APPROVING("APPROVING"),
    /**
     * 驳回
     */
    REJECT("REJECT"),
    /**
     * 撤回
     */
    CALLBACK("CALLBACK"),
    /**
     * 确认
     */
    CONFIRM("CONFIRM"),
    /**
     * 拒绝
     */
    REFUSE("REFUSE");
    /**
     * 描述
     */
    private String message;

    /**
     * 私有构造方法
     *
     * @param message
     */
    FormDataApprovalState(String message) {
        this.message = message;
    }

    /**
     * 重构toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        return message;
    }
}
