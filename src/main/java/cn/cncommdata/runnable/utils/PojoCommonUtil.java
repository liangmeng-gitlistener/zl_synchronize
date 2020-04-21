package cn.cncommdata.runnable.utils;

import cn.cncommdata.entity.CastOutput;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;

import java.util.List;
import java.util.function.Predicate;

public class PojoCommonUtil<T> {
    /**
     * 获取http与mysql中数据完全一致的数据集
     * @param httpList
     * @param dbList
     * @return
     */
    public List<T> getSameList(List<T> httpList, List<T> dbList){
        List<T> result = CollUtil.newArrayList();
        for (T http : httpList) {
            for (T db : dbList) {
                if (http.equals(db)) {
                    result.add(http);
                }
            }
        }
        return result;
    }
}
