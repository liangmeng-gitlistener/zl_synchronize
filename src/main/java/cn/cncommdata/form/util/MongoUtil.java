package cn.cncommdata.form.util;

import cn.cncommdata.form.model.DBBase;
import cn.cncommdata.form.model.FormData;
import cn.cncommdata.form.model.FormDataApproval;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author: create by leimin
 * @version: v1.0
 * @description:
 * @date:2019-04-19 18:37
 **/
@Component
public class MongoUtil {
    /**
     * 只是修改FormData状态, create by leimin
     *
     * @param formData fromData 对象
     * @param update   修改 对象
     * @return update对象
     */
    public Update getUpdateByFormDataStatus(FormData formData, Update update) {
        update.set("status", formData.getStatus());
        update.set("index", formData.getStatus().getIndex());
        update.set("form_status", formData.getFormStatus());
        update.set("editor", formData.getEditor());
        update.set("edit_time", System.currentTimeMillis());
        return update;
    }

    /**
     * 修改基础类属性, create by leimin
     *
     * @param dbBase dbBase 对象
     * @param update update 对象
     * @param type   操作类型
     * @return update对象
     */
    public Update getUpdateBase(DBBase dbBase, Update update, String type) {
        update.set("id", dbBase.getId());
        update.set("tenant_id", dbBase.getTenantId());
        update.set("deleted", dbBase.getDeleted());
        if (StringUtils.isEmpty(type) || FormConstants.CREATE.equals(type)) {
            update.set("creator", dbBase.getCreator());
            update.set("create_time", dbBase.getCreateTime());
        }
        update.set("editor", dbBase.getEditor());
        update.set("editTime", dbBase.getEditTime());
        return update;
    }

    /**
     * 修改formData所有属性, create by leimin
     *
     * @param formData formData 对象
     * @param update   update 对象
     * @param type     操作类型
     * @return update对象
     */
    public Update getUpdateFormDataAll(FormData formData, Update update, String type) {

        DBBase dbBase = new DBBase(formData.getId(), formData.getTenantId(), formData.getDeleted(), formData.getCreator(),
                formData.getCreateTime(), formData.getEditor(), formData.getEditTime());

        update = getUpdateBase(dbBase, update, type);
        update.set("form_id", formData.getFormId());
        update.set("tree_id", formData.getTreeId());
        update.set("tree_node_id", formData.getTreeNodeId());
        update.set("data", formData.getData());
        update.set("status", formData.getStatus());
        update.set("index", formData.getIndex());
        update.set("form_sources", formData.getFormSources());
        update.set("form_status", formData.getFormStatus());
        update.set("edit_time", formData.getEditTime());
        update.set("rule_ids", formData.getRuleIds());
        return update;
    }

    /**
     * 只是修改formDataApproval状态, create by leimin
     *
     * @param formDataApproval formDataApproval 对象
     * @param update           修改对象
     * @return update对象
     */
    public Update getUpdateByFormDataApproveStatus(FormDataApproval formDataApproval, Update update) {
        update.set("status", formDataApproval.getStatus());
        update.set("form_status", formDataApproval.getFormStatus());
        update.set("approval_status", formDataApproval.getApprovalStatus());
        update.set("editor", formDataApproval.getEditor());
        update.set("edit_time", System.currentTimeMillis());
        return update;
    }

    /**
     * 获取FormDataApproval 所有属性的update, create by leimin
     *
     * @param formDataApproval approval对象
     * @param update           update 对象
     * @return update对象
     */
    public Update getUpdateByAllApprove(FormDataApproval formDataApproval, Update update) {

        FormData formData = new FormData(formDataApproval.getId(), formDataApproval.getTenantId(),
                formDataApproval.getDeleted(), formDataApproval.getCreator(),
                formDataApproval.getCreateTime(), formDataApproval.getEditor(), formDataApproval.getEditTime(),
                formDataApproval.getFormId(), formDataApproval.getTreeId(), formDataApproval.getTreeNodeId(),
                formDataApproval.getData(), formDataApproval.getStatus(),
                formDataApproval.getFormSources(), formDataApproval.getFormStatus());

        update = getUpdateFormDataAll(formData, update, null);
        update.set("approval_status", formDataApproval.getApprovalStatus());
        update.set("approval_method", formDataApproval.getApprovalMethod());
        update.set("process_instance", formDataApproval.getProcessInstance());
        update.set("form_data_id", formDataApproval.getFormDataId());
        return update;
    }

}
