package cn.cncommdata.config;

import cn.cncommdata.dao.mysql.TCronTriggerDao;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

@Configuration
@Slf4j
public class CustomScheduleConfig implements SchedulingConfigurer {

    @Autowired
    private TCronTriggerDao cronTriggerDao;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 添加一个配合数据库动态执行的定时任务
        TriggerTask triggerTask = new TriggerTask(getRunnable(), getTrigger());
        taskRegistrar.addTriggerTask(triggerTask);
    }

    /**
     * 添加任务 Runnable
     * @return
     */
    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                log.info("{}执行动态任务job1=>{},线程：{}",
                        this.getClass().getSimpleName(),
                        DateUtil.formatDateTime(new Date()),
                        Thread.currentThread().getName());
            }
        };
    }

    /**
     * 设置执行周期
     * @return
     */
    private Trigger getTrigger() {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 3）从数据库获取执行周期
                String cron = getCron(1);
                if (cron != null) {
                    // 4）返回执行周期（Date）
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                } else {
                    return null;
                }
            }
        };
    }

    private String getCron(long id){
        return cronTriggerDao.queryById(id).getCron();
    }
}
