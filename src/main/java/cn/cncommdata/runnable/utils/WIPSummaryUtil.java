package cn.cncommdata.runnable.utils;

import cn.cncommdata.bean.WIPJsonBase;
import cn.cncommdata.bean.data.XYJsonData;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.entity.WipSummary;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.utils.Builder;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WIPSummaryUtil {
    /**
     * 从http获取数据集合
     * @param httpConfig
     * @return
     */
    public static WIPJsonBase getFromHTTP(HttpConfig httpConfig){
        Map<String, Object> paramMap = getParamMap(httpConfig);

        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.WIP.getUrl());
        String httpBody = HttpUtils.get(url, paramMap, httpConfig);

        return JSONUtil.toBean(httpBody, WIPJsonBase.class);
    }

    /**
     * 获取订单进度查询的param参数
     * @param httpConfig
     * @return
     */
    public static Map<String, Object> getParamMap(HttpConfig httpConfig){
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put(SysConstants.WIPParam.FORM_ID.getName(), httpConfig.getWipParam().getFormId());
        return paramMap;
    }

    /**
     * 将http请求所得bean转化为数据库需存储的list
     * @param json
     * @return
     */
    public static List<WipSummary> getWIPSummaryList(WIPJsonBase json) {
        List<WipSummary> result = CollUtil.newArrayList();

        List<XYJsonData> double_foil_blank = getFilterList(json, SysConstants.WIPNameEnum.DOUBLE_FOIL_BLANK.getExtra());
        List<XYJsonData> single_foil_blank = getFilterList(json, SysConstants.WIPNameEnum.SINGLE_FOIL_BLANK.getExtra());
        List<XYJsonData> cold_rolled_coil1 = getFilterList(json, SysConstants.WIPNameEnum.COLD_ROLLED_COIL1.getExtra());
        List<XYJsonData> cold_rolled_coil2 = getFilterList(json, SysConstants.WIPNameEnum.COLD_ROLLED_COIL2.getExtra());

        double DFBTotalWeight = 0;
        double DFBTotalVolume = 0;
        double SFBTotalWeight = 0;
        double SFBTotalVolume = 0;
        double CRC1TotalWeight = 0;
        double CRC1TotalVolume = 0;
        double CRC2TotalWeight = 0;
        double CRC2TotalVolume = 0;

        if(CollUtil.isNotEmpty(double_foil_blank)) {
            DFBTotalWeight = getSum(double_foil_blank, SysConstants.WIPStatistics.WEIGHT.getValue());
            DFBTotalVolume = getSum(double_foil_blank, SysConstants.WIPStatistics.VOLUME.getValue());
        }
        if(CollUtil.isNotEmpty(single_foil_blank)) {
            SFBTotalWeight = getSum(single_foil_blank, SysConstants.WIPStatistics.WEIGHT.getValue());
            SFBTotalVolume = getSum(single_foil_blank, SysConstants.WIPStatistics.VOLUME.getValue());
        }
        if(CollUtil.isNotEmpty(cold_rolled_coil1)) {
            CRC1TotalWeight = getSum(cold_rolled_coil1, SysConstants.WIPStatistics.WEIGHT.getValue());
            CRC1TotalVolume = getSum(cold_rolled_coil1, SysConstants.WIPStatistics.VOLUME.getValue());
        }
        if(CollUtil.isNotEmpty(cold_rolled_coil2)) {
            CRC2TotalWeight = getSum(cold_rolled_coil2, SysConstants.WIPStatistics.WEIGHT.getValue());
            CRC2TotalVolume = getSum(cold_rolled_coil2, SysConstants.WIPStatistics.VOLUME.getValue());
        }

        addToWIPList(result, SysConstants.WIPNameEnum.DOUBLE_FOIL_BLANK,
                DFBTotalWeight, DFBTotalVolume);
        addToWIPList(result, SysConstants.WIPNameEnum.SINGLE_FOIL_BLANK,
                SFBTotalWeight, SFBTotalVolume);
        addToWIPList(result, SysConstants.WIPNameEnum.COLD_ROLLED_COIL1,
                CRC1TotalWeight, CRC1TotalVolume);
        addToWIPList(result, SysConstants.WIPNameEnum.COLD_ROLLED_COIL2,
                CRC2TotalWeight, CRC2TotalVolume);

        return result;
    }

    /**
     * 获取需要插入数据库的数据
     * @param httpList
     * @param dbList
     * @return
     */
    public static List<WipSummary> needInsert(List<WipSummary> httpList, List<WipSummary> dbList){
        if (CollUtil.isEmpty(httpList) &&
                SysConstants.WIPNameEnum.values().length <= dbList.size()) {
            return CollUtil.newArrayList();
        }
        List<WipSummary> result = ObjectUtil.cloneByStream(httpList);
        List<WipSummary> alreadyInDBs = CollUtil.newArrayList();
        for (WipSummary db : dbList) {
            List<WipSummary> temp = httpList.stream().filter(http ->
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
    public static List<WipSummary> needUpdate(List<WipSummary> httpList, List<WipSummary> dbList){
        if (CollUtil.isEmpty(httpList)) {
            return CollUtil.newArrayList();
        }
        List<WipSummary> result = CollUtil.newArrayList();
        for (WipSummary http : httpList) {
            for (WipSummary db : dbList) {
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
     * 条件求和函数
     * @param list
     * @param searchStr
     * @return
     */
    private static double getSum(List<XYJsonData> list, CharSequence searchStr) {
        return list.stream()
                .filter(data -> StrUtil.contains(data.getX(), searchStr))
                .mapToDouble(XYJsonData::getValue).sum();
    }

    /**
     * 条件过滤数组
     * @param wipJsonBase
     * @param searchStr
     * @return
     */
    private static List<XYJsonData> getFilterList(WIPJsonBase wipJsonBase, String searchStr) {
        return wipJsonBase.getData().stream()
                .filter(data -> StrUtil.equals(data.getY(), searchStr))
                .collect(Collectors.toList());
    }

    /**
     * 向数据库映射的bean对象列表加入数据
     * @param list
     * @param wipName
     * @param weight
     * @param volume
     */
    private static void addToWIPList(List<WipSummary> list, SysConstants.WIPNameEnum wipName, double weight, Double volume) {
        list.add(Builder.of(WipSummary::new)
                .with(WipSummary::setWipName, wipName.getName())
                .with(WipSummary::setVolume, volume.intValue())
                .with(WipSummary::setWeight, weight)
                .with(WipSummary::setExtra, wipName.getExtra())
                .build());
    }
}
