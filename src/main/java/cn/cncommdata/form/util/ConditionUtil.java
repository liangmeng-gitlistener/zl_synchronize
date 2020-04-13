package cn.cncommdata.form.util;

import cn.cncommdata.form.model.condition.Condition;
import cn.cncommdata.form.model.condition.ConditionBase;
import cn.cncommdata.form.model.condition.Or;
import cn.cncommdata.form.util.constant.Const;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.utils
 * @date:2019-05-08
 **/
@Component
public final class ConditionUtil {

    private ConditionUtil() {
    }

    /**
     * getCondition
     *
     * @param conditionsJsonArray conditions
     * @return ConditionBase list
     */
    public List<ConditionBase> getCondition(JSONArray conditionsJsonArray) {
        List<ConditionBase> conditions = new ArrayList<ConditionBase>();
        if (!CollectionUtils.isEmpty(conditionsJsonArray)) {
            for (Object object : conditionsJsonArray) {
                if (object == null) {
                    continue;
                }
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.get(Const.TYPE) == null) {
                    continue;
                }

                if ("condition".equals(jsonObject.getString(Const.TYPE))) {
                    //需要通过递归解析具体的值，不然有数据丢失；
                    Condition condition = jsonObject.toJavaObject(Condition.class);
                    JSONArray source = jsonObject.getJSONArray("source");
                    if (source != null) {
                        condition.setSource(getCondition(source));
                    }
                    conditions.add(condition);
                } else {
                    if (CollectionUtils.isEmpty(jsonObject.getJSONArray("conditions"))) {
                        continue;
                    }
                    Or or = new Or();
                    or.setType("or");
                    or.setConditions(getCondition(jsonObject.getJSONArray("conditions")));
                    conditions.add(or);
                }
            }
        }
        return conditions;
    }
}

