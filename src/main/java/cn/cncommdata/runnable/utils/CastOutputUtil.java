package cn.cncommdata.runnable.utils;

import cn.cncommdata.bean.OutputJsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.utils.Builder;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
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
        Map<String, Object> paramMap = getParamMap(httpConfig);

        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.CAST_OUTPUT.getUrl());
        String httpBody = HttpUtils.get(url, paramMap, httpConfig);

        OutputJsonBase bean = JSONUtil.toBean(httpBody, OutputJsonBase.class);

        if (ObjectUtil.isNull(bean.getData())) {
            return CollUtil.newArrayList();
        }

        List<Map<String,String>> rows = bean.getData().getRows();
        List<CastOutput> results = rows.stream().map(row -> changeMapToCO(row))
                .collect(Collectors.toList());
        return results;
    }

    /**
     * 获取订单进度查询的param参数
     * @param httpConfig
     * @return
     */
    public static Map<String, Object> getParamMap(HttpConfig httpConfig){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(SysConstants.CastOutputParam.FORM_ID.getName(),
                httpConfig.getCastOutput().getFormId());
        JSONObject conditionJson = JSONUtil.createObj()
                .set("select_month", DateUtil.format(DateTime.now(),"yyyy-M"))
                .set("classification_type", "");
        paramMap.put(SysConstants.CastOutputParam.CONDITION.getName(),
                JSONUtil.toJsonPrettyStr(conditionJson));
        paramMap.put(SysConstants.CastOutputParam.PAGE_NUMBER.getName(),
                httpConfig.getPageNumber());
        paramMap.put(SysConstants.CastOutputParam.PAGE_SIZE.getName(),
                httpConfig.getPageSize());
        return paramMap;
    }

    /**
     * 将map类型转化为CastOutput类型
     * @param row
     * @return
     */
    private static CastOutput changeMapToCO (Map<String,String> row) {
        //  2020-04-27  根据http请求结果的修改情况
        String[] dates = StrUtil.split(row.get("日期"), "-");
        if (dates.length != 2) {
            throw new RuntimeException("http请求返回结果不符合预期");
        }
        DateTime startTime = DateUtil.date(Convert.toLong(dates[0]));
        //  END
        return Builder.of(CastOutput::new)
                .with(CastOutput::setWeight, Convert.toDouble(row.get("weight")))
                .with(CastOutput::setAlloy, row.get("合金分类"))
                .with(CastOutput::setTotalWeight, Convert.toDouble(row.get("total_weight")))
                .with(CastOutput::setYear, startTime.year())
                //  +1 的原因可以看cn.hutool.core.date.Month类
                .with(CastOutput::setMonth, startTime.month() + 1 )
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
//        List<CastOutput> result = CollUtil.newArrayList();
//        for (CastOutput http : httpList) {
//            for (CastOutput db : dbList) {
//                if (http.alreadyInDB(db)) {
//                    if (!http.equals(db)) {
//                        http.setId(db.getId());
//                        http.setUpdateTime(DateUtil.date());
//                        result.add(http);
//                    }
//                }
//            }
//        }
        List<CastOutput> result = httpList.stream().map(http -> {
            dbList.forEach(db -> {
                if (http.alreadyInDB(db)) {
                    if (!http.equals(db)) {
                        http.setId(db.getId());
                        http.setUpdateTime(DateUtil.date());
                    }
                }
            });
            return http;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 获取http与mysql中数据完全一致的数据集
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<CastOutput> getSameList(List<CastOutput> httpList, List<CastOutput> dbList){
        return RunnableUtil.getSameList(httpList, dbList);
    }
}
