package cn.cncommdata.runnable.utils;

import cn.cncommdata.bean.OutputJsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.entity.CastOutput;
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
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CastOutputUtil {
    /**
     * 从http获取数据集合
     * @param httpConfig
     * @return
     */
    public static List<CastOutput> getFromHTTP(HttpConfig httpConfig){
        List<CastOutput> results = CollUtil.newArrayList();
        Map<String, Object> paramMap = getparamMap(httpConfig);

        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.CAST_OUTPUT.getUrl());
        String httpBody = HttpUtils.get(url, paramMap, httpConfig);

        OutputJsonBase bean = JSONUtil.toBean(httpBody, OutputJsonBase.class);

        if (ObjectUtil.isNull(bean.getData())) {
            return results;
        }

        List<Map<String,String>> rows = bean.getData().getRows();
        rows.stream().forEach(
                (row) -> {
                    results.add(changeMapToCO(row));
                });
        return results;
    }

    /**
     * TODO:condition入参应该加入时间类型的参数（目前未传任何查询条件）
     * 获取订单进度查询的param参数
     * @param httpConfig
     * @return
     */
    public static Map<String, Object> getparamMap(HttpConfig httpConfig){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(SysConstants.CastOutputParam.FORM_ID.getName(), httpConfig.getCastOutput().getFormId());
        paramMap.put(SysConstants.CastOutputParam.CONDITION.getName(), httpConfig.getCastOutput().getCondition());
        paramMap.put(SysConstants.CastOutputParam.PAGE_NUMBER.getName(), httpConfig.getPageNumber());
        paramMap.put(SysConstants.CastOutputParam.PAGE_SIZE.getName(), httpConfig.getPageSize());
        return paramMap;
    }

    /**
     * 将map类型转化为CastOutput类型
     * @param row
     * @return
     */
    private static CastOutput changeMapToCO (Map<String,String> row) {
        DateTime httpDate = DateUtil.parse(row.get("日期"), DatePattern.NORM_DATE_PATTERN);
        return Builder.of(CastOutput::new)
                .with(CastOutput::setWeight, Convert.toDouble(row.get("weight")))
                .with(CastOutput::setAlloy, row.get("合金分类"))
                .with(CastOutput::setTotalWeight, Convert.toDouble(row.get("total_weight")))
                .with(CastOutput::setYear, httpDate.year())
                //  +1 的原因可以看cn.hutool.core.date.Month类
                .with(CastOutput::setMonth, httpDate.month() + 1 )
                .build();
    }

    /**
     * 获取需要插入数据库的数据
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<CastOutput> needInsert(List<CastOutput> httpList, List<CastOutput> dbList){
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<CastOutput> result = ObjectUtil.cloneByStream(httpList);
        List<CastOutput> alreadyInDBs = CollUtil.newArrayList();
        for (CastOutput db : dbList) {
            List<CastOutput> temp = httpList.stream().filter(http ->
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

    /**
     * 获取需要更新的数据库的数据
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<CastOutput> needUpdate(List<CastOutput> httpList, List<CastOutput> dbList){
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<CastOutput> result = CollUtil.newArrayList();
        for (CastOutput http : httpList) {
            for (CastOutput db : dbList) {
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
     * 获取http与mysql中数据完全一致的数据集
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<CastOutput> getSameList(List<CastOutput> httpList, List<CastOutput> dbList){
        return new PojoCommonUtil().getSameList(httpList, dbList);
    }
}
