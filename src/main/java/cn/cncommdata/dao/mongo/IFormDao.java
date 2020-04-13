package cn.cncommdata.dao.mongo;

import cn.cncommdata.form.model.FormData;
import cn.cncommdata.form.model.FormDataApproval;

import java.util.List;
import java.util.Map;

/**
 * @description： 单据持久层
 * @author： niulibing
 * @date: 2019/4/17 17:59
 */

public interface IFormDao {


    /**
     * 获取fromDataId 的状态
     *
     * @param formDataId fromData id
     * @param tenantId   企业id
     * @return form Data 的状态
     * @author leimin
     */
    FormData getByFormDataId(Long formDataId, Long tenantId);

    /**
     * 获取数据详情
     *
     * @param tenantId 企业id
     * @param formId   表单id
     * @param dataAuth 获取数据权限的key
     * @param dataIds  数据id
     * @return 查询结果
     * @author niulibing
     */
    Map<String, List<FormData>> getDataDetails(Long tenantId, Long formId, String dataAuth, List<Long> dataIds);


    /**
     * 根据dataId和formId获取
     *
     * @param tenantId 企业id
     * @param dataId   数据id
     * @param formId   表单id
     * @return 结果
     * @author niulibing
     */
    FormData findOneByDataIdAndFormId(Long tenantId, Long dataId, Long formId);

    /**
     * 通过formId获取FormDataList
     *
     * @param formId 表单id
     * @return FormDataList
     * @author niulibing
     */
    List<FormData> getFormDataListByFormId(Long formId);

    /**
     * 根据formDataId获取formData对象
     *
     * @param formDataId 主键id
     * @param tenantId   企业id
     * @return formData
     */
    FormData getFormData(Long formDataId, Long tenantId);

    /**
     * 附表中的最新数据
     *
     * @param formDataList 主表查询出的数据；
     * @return 根据主表查询出的附表数据
     */
    List<FormDataApproval> getFormDataApprovals(List<FormData> formDataList);

    /**
     * 批量获取formData
     *
     * @param formDataIds ids
     * @param tenantId    企业id
     * @param formId      表单id
     * @return formDataList
     */
    List<FormData> getFormDataByIds(List<Long> formDataIds, Long tenantId, Long formId);

    /**
     * 批量获取formData
     *
     * @param formDataIds ids
     * @param tenantId    企业id
     * @return formDataList
     * @author leimin
     */
    List<FormData> getFormDataByIds(List<Long> formDataIds, Long tenantId);

    /**
     * 批量获取formData
     *
     * @param tenantId 企业id
     * @param formId   表单id
     * @return formDataList
     */
    List<FormData> findFormDataListByFormId(Long tenantId, Long formId);

    /**
     * 获取formData
     *
     * @param formDataId 数据id
     * @param formId     表单id
     * @param tenantId   企业id
     * @return FormData
     * @author libing.niu
     */
    FormData getFormData(Long formDataId, Long formId, Long tenantId);

    /**
     * 根据origin_id和ENABLE状态去查询旧版本数据
     *
     * @param formData 条件数据
     * @return FormData
     * @author weihong.zhu
     */
    FormData getFormDataByOriginId(FormData formData);

    /**
     * find all the formData which quote target formData
     *
     * @param tenantId      enterprise id
     * @param sourceFormIds all the id which quote
     * @return all the form data which quote
     */
    List<FormData> getQuotedFormData(Long tenantId, List<Long> sourceFormIds);

    /**
     * 获取数据列表条数
     *
     * @param tenantId 企业id
     * @param formId   表单id
     * @return BaseVO
     * @author tianpeng.zhu
     */
    long getDataListCount(Long tenantId, Long formId);

    /**
     * 批量查询formId下,对应的字段值不为空的数据
     *
     * @param formQuoteField formId和字段名的映射
     * @return List<FormData>
     */
    List<FormData> checkIsQuote(Map<Long, List<String>> formQuoteField);

    /**
     * 获取，当前数据作为前置规则数据源时，所有来源与当前数据的数据
     *
     * @param sourceFormDataId 规则数据源ID
     * @param tenantId         企业ID
     * @param grantId          操作员ID
     * @return 来源与当前数据的数据
     */
    List<FormData> getBySourceFormDataId(Long sourceFormDataId, Long tenantId, Long grantId);

    /**
     * 根据卷号查询他对应的所有进程数据
     *
     * @param tenantId  企业ID
     * @param formIds   表单ID列表
     * @param condition 查询条件的json 对象
     * @return 查询结果
     */
    List<FormData> getFormDataByReelNumber(Long tenantId, String formIds, String condition);
}
