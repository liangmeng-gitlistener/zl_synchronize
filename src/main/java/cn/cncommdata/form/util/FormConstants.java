package cn.cncommdata.form.util;

/**
 * @author leimin
 * @description
 * @time: 2019/07/22
 **/
public final class FormConstants {
    /**
     * 虚拟表单的部门id
     */
    public static final Long DEPARTMENT_NUMBER = 1000000000000000001L;
    /**
     * 虚拟表单的职位id
     */
    public static final Long DUTY_NUMBER = 1000000000000000002L;
    /**
     * 虚拟表单的grant_id
     */
    public static final Long GRANT_NUMBER = 1000000000000000003L;

    /**
     * 虚拟表单的部门id
     */
    public static final String DEPARTMENT_STRING = "1000000000000000001";
    /**
     * 虚拟表单的职位id
     */
    public static final String DUTY_STRING = "1000000000000000002";
    /**
     * 虚拟表单的grant_id
     */
    public static final String GRANT_STRING = "1000000000000000003";
    /**
     * 新增操作类型String
     */
    public static final String CREATE = "create";
    /**
     * 删除操作类型String
     */
    public static final String DELETE = "delete";
    /**
     * 修改操作类型String
     */
    public static final String EDIT = "edit";
    /**
     * 规则操作类型String
     */
    public static final String RULE = "rule";
    /**
     * 导出操作类型String
     */
    public static final String EXPORT = "export";
    /**
     * 规则类型String
     */
    public static final String BACK = "back";

    /**
     * 编辑审批锁
     */
    public static final String EDIT_APPROVAL_LOCK = "edit_approval_lock";

    /**
     * 新增审批锁
     */
    public static final String CREATE_APPROVAL_LOCK = "create_approval_lock";

    /**
     * 编辑锁
     */
    public static final String EDIT_LOCK = "edit_lock";

    /**
     * 新增锁
     */
    public static final String CREATE_LOCK = "create_lock";
    private FormConstants() {
    }
}
