package cn.cncommdata.runnable.utils;

import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.enums.TaskEnum;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class RunnableUtil {
    /**
     * 在定时任务中将当前执行完成时间反写入数据库中
     * @param cronTriggerDao    定时任务dao
     * @param task              定时任务ID
     */
    public static int UpdateLastRunTime(TCronTriggerDao cronTriggerDao, TaskEnum task){
        return cronTriggerDao.updateLastRunTimeById(DateUtil.date(), task.getTaskId());
    }

    /**
     * 获取http与mysql中数据完全一致的数据集
     * @param httpList
     * @param dbList
     * @return
     */
    public static <T> List<T> getSameList(List<T> httpList, List<T> dbList){
        List<T> result = CollUtil.newArrayList();
        httpList.stream().forEach(
                http -> {
                    CollUtil.addAllIfNotContains(result,
                            dbList.stream().filter(http::equals)
                                    .collect(Collectors.toList()));
                });
//        为加深对流式编程的理解自己写的方法，一般复杂的不会使用这种写法
//        for (T http : httpList) {
//            for (T db : dbList) {
//                if (http.equals(db)) {
//                    result.add(http);
//                }
//            }
//        }
        return result;
    }
}
