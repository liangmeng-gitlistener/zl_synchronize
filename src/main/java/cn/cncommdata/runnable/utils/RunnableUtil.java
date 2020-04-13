package cn.cncommdata.runnable.utils;

import cn.cncommdata.dao.mysql.TCronTriggerDao;
import cn.cncommdata.enums.TaskEnum;
import cn.hutool.core.date.DateUtil;

public class RunnableUtil {
    /**
     * 在定时任务中将当前执行完成时间反写入数据库中
     * @param cronTriggerDao    定时任务dao
     * @param taskId            定时任务ID
     */
    public static int UpdateLastRunTime(TCronTriggerDao cronTriggerDao, Long taskId){
        return cronTriggerDao.updateLastRunTimeById(DateUtil.date(), TaskEnum.TEST_TASK.getTaskId());
    }
}
