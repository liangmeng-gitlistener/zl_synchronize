package cn.cncommdata.runnable.utils;

import cn.cncommdata.bean.OutputJsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.entity.ColdRollOutput;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.utils.Builder;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ColdRollOutputUtil {
    public static List<ColdRollOutput> getFromHTTP(HttpConfig httpConfig) {
        List<ColdRollOutput> results = CollUtil.newArrayList();
        Map<String, Object> paramMap = getparamMap(httpConfig);

        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.COLD_ROLL_OUTPUT.getUrl());
        String httpBody = HttpUtils.get(url, paramMap, httpConfig);

        OutputJsonBase bean = JSONUtil.toBean(httpBody, OutputJsonBase.class);
        if (ObjectUtil.isNull(bean.getData())) {
            return results;
        }
        List<Map<String,String>> rows = bean.getData().getRows();
        rows.stream().forEach(row -> {
                    results.add(changeMapToCRO(row));
                });
        return results;
    }

    /**
     * 获取http与mysql中数据完全一致的数据集
     * @param httpList
     * @param dbList
     * @return
     */
    public static Collection<ColdRollOutput> getSameList(List<ColdRollOutput> httpList, List<ColdRollOutput> dbList) {
        return RunnableUtil.getSameList(httpList, dbList);
    }

    public static List<ColdRollOutput> needInsert(List<ColdRollOutput> httpList, List<ColdRollOutput> dbList) {
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<ColdRollOutput> result = ObjectUtil.cloneByStream(httpList);
        List<ColdRollOutput> alreadyInDBs = CollUtil.newArrayList();
        for (ColdRollOutput db : dbList) {
            List<ColdRollOutput> temp = httpList.stream().filter(http ->
                    http.alreadyInDB(db)
            ).collect(Collectors.toList());
            alreadyInDBs.addAll(temp);
        }
        result.removeAll(alreadyInDBs);
        result.stream().forEach(http -> {
            http.setCreateTime(DateUtil.date());
            http.setUpdateTime(DateUtil.date());
        });
        return result;
    }

    public static List<ColdRollOutput> needUpdate(List<ColdRollOutput> httpList, List<ColdRollOutput> dbList) {
        List<ColdRollOutput> result = CollUtil.newArrayList();
        if (CollUtil.isEmpty(httpList)) {
            return result;
        }
        for (ColdRollOutput http : httpList) {
            for (ColdRollOutput db : dbList) {
                if (http.alreadyInDB(db)) {
                    if (!http.equals(db)) {
                        http.setId(db.getId());
                        http.setUpdateTime(DateUtil.date());
                        result.add(http);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取订单进度查询的param参数
     * @param httpConfig
     * @return
     */
    public static Map<String, Object> getparamMap(HttpConfig httpConfig){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(SysConstants.ColdRollOutputParam.FORM_ID.getName(), httpConfig.getColdRollOutput().getFormId());
        JSONObject conditionJson = JSONUtil.createObj()
                .set("select_month", DateUtil.format(DateTime.now(),"yyyy-M"))
                .set("classification_type", "");
        paramMap.put(SysConstants.ColdRollOutputParam.CONDITION.getName(),
                JSONUtil.toJsonPrettyStr(conditionJson));
        paramMap.put(SysConstants.ColdRollOutputParam.PAGE_NUMBER.getName(), httpConfig.getPageNumber());
        paramMap.put(SysConstants.ColdRollOutputParam.PAGE_SIZE.getName(), httpConfig.getPageSize());
        return paramMap;
    }

    /**
     * 将map类型转化为CastOutput类型
     * @param row
     * @return
     */
    private static ColdRollOutput changeMapToCRO (Map<String,String> row) {
        //  2020-04-27  根据http请求结果的修改情况
        String[] dates = StrUtil.split(row.get("日期"), "-");
        if (dates.length != 2) {
            throw new RuntimeException("http请求返回结果不符合预期");
        }
        DateTime startTime = DateUtil.date(Convert.toLong(dates[0]));
        //  END
        return Builder.of(ColdRollOutput::new)
                .with(ColdRollOutput::setWeight, Convert.toDouble(row.get("weight")))
                .with(ColdRollOutput::setAlloy, row.get("合金分类"))
                .with(ColdRollOutput::setTotalWeight, Convert.toDouble(row.get("total_weight")))
                .with(ColdRollOutput::setYear, startTime.year())
                //  +1 的原因可以看cn.hutool.core.date.Month类
                .with(ColdRollOutput::setMonth, startTime.month() + 1 )
                .build();
    }
}