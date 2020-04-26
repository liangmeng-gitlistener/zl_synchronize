package cn.cncommdata.runnable.utils;

import cn.cncommdata.bean.ScheduleJsonBase;
import cn.cncommdata.bean.data.schedule.row.JsonRows;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.entity.OrderSchedule;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.utils.Builder;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderProgressUtil {
    /**
     * 从http获取数据集合
     * @param httpConfig
     * @return
     */
    public static List<OrderSchedule> getFromHTTP(HttpConfig httpConfig){
        List<OrderSchedule> results = CollUtil.newArrayList();
        Map<String, Object> paramMap = getparamMap(httpConfig);
        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.ORDER_PROGRESS.getUrl());
        String httpBody = HttpUtils.get(url, paramMap, httpConfig);

        ScheduleJsonBase bean = JSONUtil.toBean(httpBody, ScheduleJsonBase.class);
        if (ObjectUtil.isNull(bean.getData())) {
            return results;
        }
        List<JsonRows> rows = bean.getData().getRows();
        rows.stream().filter(row -> row.getStatus().equals("ENABLE")).forEach(
                (row) -> {
                    results.add(changeJRToOS(row));
                });
        return results;
    }

    /**
     * 获取订单进度查询的param参数
     * @param httpConfig
     * @return
     */
    public static Map<String, Object> getparamMap(HttpConfig httpConfig){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(SysConstants.OrderProgressParam.FORM_ID.getName(), httpConfig.getOrderProgress().getFormId());
        paramMap.put(SysConstants.OrderProgressParam.CONDITION.getName(), httpConfig.getOrderProgress().getCondition());
        paramMap.put(SysConstants.OrderProgressParam.PAGE_NUMBER.getName(), httpConfig.getPageNumber());
        paramMap.put(SysConstants.OrderProgressParam.PAGE_SIZE.getName(), httpConfig.getPageSize());
        return paramMap;
    }

    /**
     *
     * @param row
     * @return
     */
    private static OrderSchedule changeJRToOS (JsonRows row) {
        return Builder.of(OrderSchedule::new)
                .with(OrderSchedule::setProcessProductOrder, row.getData().get("流程生产订单编号").get("value"))
                .with(OrderSchedule::setProductOrderNum, row.getData().get("生产订单编号").get("value"))
                .with(OrderSchedule::setProductName, row.getData().get("产品名称").get("value"))
                .with(OrderSchedule::setCustomer, row.getData().get("客户").get("value"))
                .with(OrderSchedule::setAlloyState, row.getData().get("合金状态").get("value"))
                .with(OrderSchedule::setPlanVolume, Convert.toInt(row.getData().get("本订单计划卷数").get("value")))
                .with(OrderSchedule::setCompletedVolume, Convert.toInt(row.getData().get("实际生产卷数").get("value")))
                .build();
    }

    /**
     * 获取需要插入数据库的数据
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<OrderSchedule> needInsert(List<OrderSchedule> httpList, List<OrderSchedule> dbList){
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<OrderSchedule> result = ObjectUtil.cloneByStream(httpList);
        List<OrderSchedule> alreadyInDBs = CollUtil.newArrayList();
        for (OrderSchedule db : dbList) {
            List<OrderSchedule> temp = httpList.stream().filter(http ->
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
    public static List<OrderSchedule> needUpdate(List<OrderSchedule> httpList, List<OrderSchedule> dbList){
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<OrderSchedule> result = CollUtil.newArrayList();
        for (OrderSchedule http : httpList) {
            for (OrderSchedule db : dbList) {
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
    public static List<OrderSchedule> getSameList(List<OrderSchedule> httpList, List<OrderSchedule> dbList){
        return new PojoCommonUtil().getSameList(httpList, dbList);
    }
}
