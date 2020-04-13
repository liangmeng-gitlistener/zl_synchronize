package cn.cncommdata.runnable;

import cn.cncommdata.dao.mysql.TCronTriggerDao;
import cn.cncommdata.entity.TCronTrigger;
import cn.cncommdata.enums.TaskEnum;
import cn.cncommdata.utils.ApplicationContextProvider;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyTest implements Runnable {
    private TCronTriggerDao cronTriggerDao;

    @Override
    public void run() {
        log.info("{}执行动态任务job3=>{},线程：{}",
                this.getClass().getSimpleName(),
                DateUtil.now(),
                Thread.currentThread().getName());

        //  反写数据库执行时间
        this.cronTriggerDao = ApplicationContextProvider.getBean(TCronTriggerDao.class);
        TCronTrigger cronTrigger = cronTriggerDao.queryById(TaskEnum.TEST_TASK.getTaskId());
        cronTrigger.setLastRunTime(DateUtil.date());
        cronTriggerDao.update(cronTrigger);
    }
}