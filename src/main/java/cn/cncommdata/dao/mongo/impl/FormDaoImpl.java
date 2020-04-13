package cn.cncommdata.dao.mongo.impl;

import cn.cncommdata.dao.mongo.IFormDao;
import cn.cncommdata.form.enums.FormDataState;
import cn.cncommdata.form.model.FormData;
import cn.cncommdata.form.model.FormDataApproval;
import cn.cncommdata.form.model.SearchCondition;
import cn.cncommdata.form.model.condition.Condition;
import cn.cncommdata.form.model.condition.ConditionBase;
import cn.cncommdata.form.model.condition.Or;
import cn.cncommdata.form.model.fielddata.DataBase;
import cn.cncommdata.form.model.fielddata.TableData;
import cn.cncommdata.form.model.fielddata.TableRow;
import cn.cncommdata.form.util.ConditionUtil;
import cn.cncommdata.form.util.constant.Const;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * @author: create by leimin
 * @version: v1.0
 * @description:
 * @date:2019-04-19 08:43
 **/
@Slf4j
@Component
public class FormDaoImpl implements IFormDao {
    /**
     * mongodb 连接池
     */
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 引用基础工具方法
     */
    @Autowired
    private ConditionUtil conditionUtil;


    /**
     * 获取fromDataId 的状态
     *
     * @param formDataId fromData id
     * @return form Data 的状态
     * @author leimin
     */
    @Override
    public FormData getByFormDataId(Long formDataId, Long tenantId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(formDataId).and("tenant_id").is(tenantId));

        return mongoTemplate.findOne(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 查询formData
     *
     * @param formDataId 数据id
     * @param formId     表单id
     * @param tenantId   企业id
     * @return FormData
     */
    @Override
    public FormData getFormData(Long formDataId, Long formId, Long tenantId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(formDataId).and("form_id").is(formId).and("tenant_id").is(tenantId));

        return mongoTemplate.findOne(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * find all the formData which quote target formData
     *
     * @param tenantId      enterprise id
     * @param sourceFormIds all the id which quote
     * @return all the form data which quote
     */
    @Override
    public List<FormData> getQuotedFormData(Long tenantId, List<Long> sourceFormIds) {

        Query query = new Query();
        query.addCriteria(Criteria.where("tenant_id").is(tenantId).and("form_id").in(sourceFormIds));

        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }


    /**
     * 获取数据列表条数
     *
     * @param tenantId 企业id
     * @param formId   表单id
     * @return BaseVO
     * @author tianpeng.zhu
     */
    @Override
    public long getDataListCount(Long tenantId, Long formId) {
        Query queryCount = new Query();
        queryCount.addCriteria(Criteria.where("tenant_id").is(tenantId).and("form_id").is(formId).
                and("status").ne(FormDataState.DELETED.toString()));
        long count = mongoTemplate.count(queryCount, FormData.class);
        return count;
    }

    /**
     * 根据origin_id和ENABLE状态去查询旧版本数据
     *
     * @param formData 条件数据
     * @return FormData
     * @author weihong.zhu
     */
    @Override
    public FormData getFormDataByOriginId(FormData formData) {
        Query query = new Query();
        query.addCriteria(Criteria.where("origin_id").is(formData.getOriginId()).and("status")
                .is(FormDataState.ENABLE.getMessage()).and("form_id").is(formData.getFormId()).and("tenant_id")
                .is(formData.getTenantId()));

        return mongoTemplate.findOne(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 通过formId获取formDataList
     *
     * @param formId 表单id
     * @return formDataList
     */
    @Override
    public List<FormData> getFormDataListByFormId(Long formId) {
        Query query = new Query(Criteria.where("form_id").is(formId));
        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 根据formDataId获取formDataVO对象
     *
     * @param formDataId 主键id
     * @param tenantId   企业id
     * @return formDataVO对象
     */
    @Override
    public FormData getFormData(Long formDataId, Long tenantId) {
        Query query = new Query(Criteria.where("_id").is(formDataId).and("tenant_id").is(tenantId));
        return mongoTemplate.findOne(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 通过condition 匹配第二层的数据，返回匹配的List<RowId>,封装到selectedRows字段
     *
     * @param formDataList 所有需要匹配的formData
     * @param condition    匹配的条件综合体
     */
    private void getRowIdByCondition(List<FormData> formDataList, String condition) {

        if (CollectionUtils.isEmpty(formDataList) || StringUtils.isEmpty(condition)) {
            return;
        }

        //解析condition为List<ConditionBase>
        SearchCondition searchCondition = getModelFormConditionString(condition);
        if (CollectionUtils.isEmpty(searchCondition.getConditions())) {
            return;
        }

        for (FormData formData : formDataList) {

            //遍历fomData第一层数据,获取第一层的bool;主要是status字段；（但是，condition必须遍历两层，查找其中的第一层的条件字段）
            //单独处理status字段；
            Boolean bool = getStatusMatchResult(formData, searchCondition.getConditions());
            if (!bool) {
                continue;
            }

            //遍历fomData第二层数据，获取第二层的bool;匹配调价是condition中的两层数据中的带点的条件字段；
            //如果bool为true;将id加rowIds
            List<Long> rowIds = getSecondMatchRowIds(formData.getData(), searchCondition.getConditions());
            if (!CollectionUtils.isEmpty(rowIds)) {
                formData.setSelectedRows(rowIds);
            }

        }
    }

    /**
     * 遍历fomData第二层数据，获取第二层的bool;匹配调价是condition中的两层数据中的带点的条件字段；
     *
     * @param data       formData 的第一层Data
     * @param conditions 匹配的条件对象
     * @return 匹配的rowId的list
     */
    private List<Long> getSecondMatchRowIds(Map<String, DataBase> data, List<ConditionBase> conditions) {

        if (CollectionUtils.isEmpty(conditions)) {
            return null;
        }

        List<Long> rowIds = new ArrayList<>();
        for (String name : data.keySet()) {

            DataBase dataBase = data.get(name);
            if (!Const.LIST.equals(dataBase.getType())) {
                continue;

            }
            TableData tableData = (TableData) dataBase;
            if (CollectionUtils.isEmpty(tableData.getValue())) {
                continue;

            }
            //验证每条tableData是否可以匹配
            for (TableRow tableRow : tableData.getValue()) {
                //获取取每条数据的匹配结果
                if (getStatusEachRow(tableRow, name, conditions)) {
                    rowIds.add(tableRow.getId());
                }
            }
        }

        return rowIds;
    }

    /**
     * 获取每个rowData 的匹配结果
     *
     * @param tableRow   匹配对象
     * @param name       table的name
     * @param conditions 匹配的条件
     * @return 匹配结果
     */
    private Boolean getStatusEachRow(TableRow tableRow, String name, List<ConditionBase> conditions) {

        Boolean bool = false;

        for (ConditionBase conditionBase : conditions) {

            if (!"or".equals(conditionBase.getType())) {

                Condition conditioner = (Condition) conditionBase;
                String[] split = conditioner.getField().split("\\.");
                if (split.length != Const.TWO || !conditioner.getField().contains(name + ".") || StringUtils.isEmpty(split[1])) {
                    continue;
                }

                //获取行字段中匹配condition的字段的并集的结果；发现false,直接返回
                Map<String, DataBase> value = tableRow.getValue();
                Set<String> secondLevelNames = value.keySet();
                if (secondLevelNames.contains(split[1])) {

                    DataBase dataBase = value.get(split[1]);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(dataBase));
                    bool = getMatchResult(jsonObject, conditioner);
                }
                if (!bool) {
                    return bool;
                }
                // or对象与其平级的对象取交集；or对象中的子类取并集
            } else {
                Or or = (Or) conditionBase;
                List<ConditionBase> list = or.getConditions();
                bool = getStatusInOr(tableRow, name, list);
            }

        }
        return bool;
    }

    /**
     * 获取or对象中的子类的条件的匹配的结果，or对象与其平级的对象取交集；or对象中的子类取并集
     *
     * @param tableRow   当前的数据对象
     * @param name       table的name
     * @param conditions 所有的条件的list
     * @return 匹配结果，
     */
    private Boolean getStatusInOr(TableRow tableRow, String name, List<ConditionBase> conditions) {

        if (CollectionUtils.isEmpty(conditions)) {
            return true;
        }

        for (ConditionBase conditionBase : conditions) {
            Condition conditioner = (Condition) conditionBase;
            String[] split = conditioner.getField().split("\\.");
            if (split.length != Const.TWO || !conditioner.getField().contains(name + ".") || StringUtils.isEmpty(split[1])) {
                continue;
            }

            //获取行字段中匹配condition的字段的并集的结果；发现false,直接返回
            Map<String, DataBase> value = tableRow.getValue();
            Set<String> secondLevelNames = value.keySet();
            if (secondLevelNames.contains(split[1])) {

                DataBase dataBase = value.get(split[1]);
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(dataBase));
                if (getMatchResult(jsonObject, conditioner)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 根据rowData字段中的值匹配condition中条件传的value
     *
     * @param jsonObject    rowData 对象
     * @param conditionBase 条件对象
     * @return 匹配结果
     */
    private Boolean getMatchResult(JSONObject jsonObject, Condition conditionBase) {

        String value = jsonObject.getString(Const.VALUE);
        String type = jsonObject.getString(Const.TYPE);
        Object conditionValue = conditionBase.getValue();
        Boolean bool = false;

        String quoteSourceId = "quoteSourceId";
        if (Const.QUOTE.equals(type)) {
            value = jsonObject.getString(quoteSourceId);
        }
        String leftBracket = "[";
        String valueStr = "value";
        if (Const.SELECT.equals(type)) {
            value = jsonObject.getString(valueStr);
            if (!conditionValue.toString().contains(leftBracket)) {
                value = value.substring(Const.TWO, value.length() - Const.TWO);
            }
        }
        switch (conditionBase.getOperate()) {
            case "等于":
                if (!Const.NUMBER.equals(type) && value.equals(conditionValue.toString())) {
                    bool = true;
                } else if (Const.NUMBER.equals(type) && Double.parseDouble(value) == Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "小于":
                if (Double.parseDouble(value) < Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "大于":
                if (Double.parseDouble(value) > Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "不等于":
                if (!Const.NUMBER.equals(type) && !value.equals(conditionValue.toString())) {
                    bool = true;
                } else if (Const.NUMBER.equals(type) && Double.parseDouble(value) != Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "小于等于":
                if (Double.parseDouble(value) <= Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "大于等于":
                if (Double.parseDouble(value) >= Double.valueOf(conditionValue.toString())) {
                    bool = true;
                }
                break;
            case "在其中":
                if (value.contains(conditionValue.toString())) {
                    bool = true;
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * 匹配Status字段是否匹配
     *
     * @param formData   formData
     * @param conditions 所有的条件list
     * @return 是否匹配, 不匹配返回false, 匹配或没有匹配对象返回true;
     */
    private Boolean getStatusMatchResult(FormData formData, List<ConditionBase> conditions) {

        if (CollectionUtils.isEmpty(conditions)) {
            return true;
        }

        Boolean b = true;
        for (ConditionBase conditionBase : conditions) {
            if (!"or".equals(conditionBase.getType())) {
                Condition condition = (Condition) conditionBase;
                if ("status".equals(condition.getField()) && !formData.getStatus().equals(condition.getValue().toString())) {
                    b = false;
                }

            } else {
                Or or = (Or) conditionBase;
                b = getStatusMatchResult(formData, or.getConditions());
            }
        }

        return b;
    }

    /**
     * 将conditionString解析为条件对象
     *
     * @param condition 条件对象的jsonString
     * @return 条件对象
     */
    private SearchCondition getModelFormConditionString(String condition) {
        //将condition的jsonString转化为 model
        SearchCondition searchCondition = JSON.parseObject(condition, SearchCondition.class);

        if (!CollectionUtils.isEmpty(searchCondition.getConditions())) {
            //获取内部数据，防止数据解析丢失；重新注入
            JSONObject searchConditionJson = (JSONObject) JSONObject.parse(condition);
            JSONArray conditions = searchConditionJson.getJSONArray("conditions");
            List<ConditionBase> conditionList = conditionUtil.getCondition(conditions);

            //拼接查询条件；
            searchCondition.setConditions(conditionList);
        }

        return searchCondition;
    }

    /**
     * 附表中的最新数据
     *
     * @param formDataList 主表查询出的数据；
     * @return 根据主表查询出的附表数据
     */
    @Override
    public List<FormDataApproval> getFormDataApprovals(List<FormData> formDataList) {
        List<FormDataApproval> formDatas = null;

        //第一次替换主表中的数据
        List<Long> ids = new ArrayList<>();
        for (FormData f : formDataList) {
            if ((FormDataState.EDITING.equals(f.getStatus()) || FormDataState.APPROVAL_LOCK.equals(f.getStatus())
                    || FormDataState.DRAFT.equals(f.getStatus()))) {
                ids.add(f.getId());
            }
        }

        //根据主表数据去查询附表数据；
        List<String> statusList = Arrays.asList("DRAFT", "APPROVING", "CALLBACK", "REJECT");
        formDatas = mongoTemplate.find(new Query(Criteria.where("form_data_id").in(ids).and("approval_status").in(statusList)),
                FormDataApproval.class);
        return formDatas;
    }

    /**
     * 获取数据详情
     *
     * @param tenantId 企业id
     * @param formId   表单id
     * @param dataAuth 获取数据权限的key
     * @param dataIds  数据id
     * @return data
     * @author niulibing
     */
    @Override
    public Map<String, List<FormData>> getDataDetails(Long tenantId, Long formId, String dataAuth, List<Long> dataIds) {

        Map<String, List<FormData>> dataMap = new HashMap<>();
        Query query = new Query();

        //新增dataDetails的鉴权，新增creator In (grantIds)校验  zhiLong.xu修改
        if (!Const.DATA_AUTH.equals(dataAuth)) {

//            List<Long> grantIds = authorizationDoClient.getDataAuthTypeByRedisKey(dataAuth);

//            if (!CollectionUtils.isEmpty(grantIds)) {
            query.addCriteria(Criteria.where("form_id").is(formId).and("_id").in(dataIds).
                    and("tenant_id").is(tenantId).and("creator"));
            List<FormData> formDatas = mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
            dataMap.put(Const.AUTHORITY_QUERY_DATA, formDatas);
//            }
            return dataMap;
        }
        query.addCriteria(Criteria.where("form_id").is(formId).and("_id").in(dataIds).and("tenant_id").is(tenantId));

        List<FormData> formDatas = mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
        dataMap.put(Const.NO_AUTHORITY_QUERY_DATA, formDatas);
        return dataMap;

    }

    /**
     * 根据数据id和formId获取FormData数据
     *
     * @param tenantId 企业id
     * @param dataId   数据id
     * @param formId   表单id
     * @return formData对象
     * @author libing.niu
     */
    @Override
    public FormData findOneByDataIdAndFormId(Long tenantId, Long dataId, Long formId) {
        //构建查询对象
        Query query = new Query(Criteria.where("_id").is(dataId).and("form_id").is(formId).and("tenant_id").is(tenantId));
        //查询
        return mongoTemplate.findOne(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 批量获取formData
     *
     * @param formDataIds ids
     * @param tenantId    企业id
     * @param formId      表单id
     * @return formDataList
     */
    @Override
    public List<FormData> getFormDataByIds(List<Long> formDataIds, Long tenantId, Long formId) {

        // 构建查询对象
        Query query = new Query(Criteria.where("_id").in(formDataIds).and("form_id").is(formId).and("tenant_id").is(tenantId));
        // 返回时排序不变
//        query.with(commonSort());
        // 查询
        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 批量获取formData
     *
     * @param formDataIds ids
     * @param tenantId    企业id
     * @return formDataList
     */
    @Override
    public List<FormData> getFormDataByIds(List<Long> formDataIds, Long tenantId) {

        // 构建查询对象
        Query query = new Query(Criteria.where("_id").in(formDataIds).and("tenant_id").is(tenantId));
        // 查询
        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    @Override
    public List<FormData> findFormDataListByFormId(Long tenantId, Long formId) {
        // 构建查询对象
        Query query = new Query(Criteria.where("form_id").is(formId).and("tenant_id").is(tenantId));
        // 查询
        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }

    /**
     * 批量查询formId下,对应的字段值不为空的数据
     *
     * @param formQuoteField formId和字段名的映射
     * @return 返回查询的结果
     */
    @Override
    public List<FormData> checkIsQuote(Map<Long, List<String>> formQuoteField) {

        List<Criteria> criteriaOut = new ArrayList<>();

        for (Map.Entry<Long, List<String>> entry : formQuoteField.entrySet()) {
            if (CollectionUtils.isEmpty(entry.getValue())) {
                continue;
            }

            List<Criteria> criteriaIn = new ArrayList<>();

            for (String filedName : entry.getValue()) {
                if (StringUtils.isEmpty(filedName)) {
                    continue;
                }

                Criteria criteriaField = new Criteria();
                if (!filedName.contains(".")) {
                    criteriaField.and("data." + filedName + ".value").ne(null);
                }

                if (filedName.contains(".")) {
                    String[] split = filedName.split("\\.");
                    criteriaField.and("data." + split[0] + ".value.value." + split[1] + ".value").ne(null);
                }
                criteriaIn.add(criteriaField);
            }

            Criteria criteria = new Criteria();
            criteria.and("form_id").is(entry.getKey());

            if (criteriaIn.size() > 1) {
                criteria.orOperator(criteriaIn.toArray(new Criteria[criteriaIn.size()]));
            } else if (criteriaIn.size() == 1) {
                criteria.orOperator(criteriaIn.get(0));
            }
            criteriaOut.add(criteria);
        }

        Criteria finao = new Criteria();
        if (!CollectionUtils.isEmpty(criteriaOut)) {
            finao.orOperator(criteriaOut.toArray(new Criteria[criteriaOut.size()]));
        }

        Query query = new Query();
        query.addCriteria(finao);
        return mongoTemplate.find(query, FormData.class, "formData");
    }

    /**
     * 获取，当前数据作为前置规则数据源时，所有来源与当前数据的数据
     *
     * @param sourceFormDataId 规则数据源ID
     * @param tenantId         企业ID
     * @param grantId          操作员ID
     * @return 来源与当前数据的数据
     */
    @Override
    public List<FormData> getBySourceFormDataId(Long sourceFormDataId, Long tenantId, Long grantId) {

        Criteria criteria = new Criteria();
        criteria.and("tenant_id").is(tenantId).and("form_sources.data_id").is(sourceFormDataId);

        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, FormData.class, "formData");
    }

    /**
     * 根据卷号查询他对应的所有进程数据
     *
     * @param tenantId  企业ID
     * @param formIds   表单ID列表
     * @param condition 查询条件的json 对象
     * @return 查询结果
     */
    @Override
    public List<FormData> getFormDataByReelNumber(Long tenantId, String formIds, String condition) {

        List<Long> formIdList = JSONArray.parseArray(formIds, Long.class);
        if (CollectionUtils.isEmpty(formIdList)) {
            return null;
        }

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("tenant_id").is(tenantId).and("form_id").in(formIdList);

        if (!StringUtils.isEmpty(condition)) {
            Map<String, String> conditionJson = JSONObject.parseObject(condition, Map.class);
            for (Map.Entry<String, String> entry : conditionJson.entrySet()) {

                if (entry == null) {
                    continue;
                }

                if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue())) {

                    criteria.and("data." + entry.getKey() + ".value").regex(entry.getValue());
                }
            }
        }

        query.addCriteria(criteria);

        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }
}
