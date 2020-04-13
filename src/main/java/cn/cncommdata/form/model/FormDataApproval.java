package cn.cncommdata.form.model;

import cn.cncommdata.form.enums.FormDataApprovalState;
import cn.cncommdata.form.util.constant.Const;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model
 * @date:2019-04-11
 **/
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Const.COLLECTION_FORM_APPROVAL)
public class FormDataApproval extends FormData {

    /**
     * 审批状态定义，由系统定义为：draft未提交、approving审批中、reject驳回、callback撤回、confirm确认、refuse拒绝
     */
    @Field("approval_status")
    private FormDataApprovalState approvalStatus;
    /**
     * 审批操作，判定当前流程，由系统定义为：create、edit、delete
     */
    @Field("approval_method")
    private String approvalMethod;
    /**
     * 绑定审批流的流程Id
     */
    @Field("process_instance")
    private String processInstance;
    /**
     * 数据源，待增加或更新删除的formDataId
     */
    @Field("form_data_id")
    private Long formDataId;

}
