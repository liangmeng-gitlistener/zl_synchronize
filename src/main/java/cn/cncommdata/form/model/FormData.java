package cn.cncommdata.form.model;

import cn.cncommdata.form.enums.FormDataState;
import cn.cncommdata.form.model.fielddata.DataBase;
import cn.cncommdata.form.util.constant.Const;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model
 * @date:2019-04-11
 **/

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Const.COLLECTION_FORM_DATA)
public class FormData extends DBBase {

    /**
     * 类型，固定为data
     */
    private String type = "data";

    /**
     * 表单Id
     */
    @Field("form_id")
    private Long formId;
    /**
     * 分类树Id
     */
    @Field("tree_id")
    private Long treeId;
    /**
     * 分类树节点Id
     */
    @Field("tree_node_id")
    private Long treeNodeId;
    /**
     * 数据
     */
    @JsonRawValue
    private Map<String, DataBase> data;
    /**
     * 表单状态：approval_delete删除审核中
     * approval_edit审核中、
     * approval_lock审核锁定
     * approval_create审核新建中，不予显示
     * deleted已删除、enable正常使用、draft草稿、editing修改中
     */
    private FormDataState status;

    /**
     * 表单状态副本
     */
    private Integer index;
    /**
     * 数据来源路径
     */
    @Field("form_sources")
    private List<FormSource> formSources;
    /**
     * 自定义状态
     */
    @Field("form_status")
    private String formStatus;
    /**
     * 复制对象的id
     */
    @Field("copy_source_id")
    private Long copySourceId;

    /**
     * 选中行的ids
     */
    @Field("selected_rows")
    private List<Long> selectedRows;

    /**
     * 选中行的rule_ids
     */
    @Field("rule_ids")
    private List<Long> ruleIds;

    /**
     * 审批的进度id
     */
    @Field("process_id")
    private Long processId;
    /**
     * 源版本id
     */
    @Field("origin_id")
    private Long originId;

    /**
     * 当前版本号
     */
    private String version;



    public FormData(Long id, Long tenantId, Boolean deleted, Long creator, Long createTime, Long editor, Long editTime,
                    Long formId, Long treeId, Long treeNodeId, Map<String, DataBase> data, FormDataState status,
                    List<FormSource> formSources, String formStatus) {
        super(id, tenantId, deleted, creator, createTime, editor, editTime);
        this.formId = formId;
        this.treeId = treeId;
        this.treeNodeId = treeNodeId;
        this.data = data;
        this.status = status;
        this.formSources = formSources;
        this.formStatus = formStatus;
    }

    public FormData(Long id, Long formId, Long treeId, Long treeNodeId, Map<String, DataBase> data,
                    FormDataState status, List<FormSource> formSources, String formStatus) {
        this.setId(id);
        this.formId = formId;
        this.treeId = treeId;
        this.treeNodeId = treeNodeId;
        this.data = data;
        this.status = status;
        this.formSources = formSources;
        this.formStatus = formStatus;

    }

    /**
     * 初始化formData基本参数
     *
     * @param formId   表单id
     * @param grantId  操作员id
     * @param tenantId tenantId
     */
    public void initFormData(Long formId, Long grantId, Long tenantId) {
        this.setCreator(grantId);
        this.setCreateTime(System.currentTimeMillis());
        this.setEditTime(this.getCreateTime());
        this.setFormId(formId);
        this.setTenantId(tenantId);
    }

}
