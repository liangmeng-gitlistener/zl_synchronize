package cn.cncommdata.config;

import cn.cncommdata.entity.TCronTrigger;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Configuration
public class ScheduleConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Resource
    public  void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        ScheduleConfig.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    /**
     * 存放所有启动定时任务对象，极其重要
     */
    private static HashMap<Long, ScheduledFuture<?>> scheduleMap = new HashMap<>();


    /**
     * @param cronTriggers 动态设置定时任务方法
     *              <p>
     *              此方法是真正的动态实现启停和时间周期的关键，你可以针对自己的业务来调用，你对库中的动态数据修改后来调用此方法，每个Cron对象必须要包含，执行周期（cron.getCron()），启停状态（cron.getCronStatus()），执行的类（cron.getCronClass()）
     */
    public List<TCronTrigger> startCron(List<TCronTrigger> cronTriggers) {
        List<TCronTrigger> result = CollUtil.newArrayList();
        try {
            if (ObjectUtil.isNotEmpty(cronTriggers)) {
                //遍历所有库中动态数据，根据库中class取出所属的定时任务对象进行关闭，每次都会把之前所有的定时任务都关闭，根据新的状态重新启用一次，达到最新配置
                for (TCronTrigger cron : cronTriggers) {
                    ScheduledFuture<?> scheduledFuture = scheduleMap.get(cron.getId());
                    //一定判空否则出现空指针异常，ToolUtil为自己写的工具类此处只需要判断对象是否为空即可
                    if ((scheduledFuture != null)) {
                        scheduledFuture.cancel(true);
                    }
                }
                //遍历库中数据，之前已经把之前所有的定时任务都停用了，现在判断库中如果是启用的重新启用并读取新的数据，把开启的数据对象保存到定时任务对象中以便下次停用
                for (TCronTrigger cron : cronTriggers) {
                    //判断当前定时任务是否有效，COMMON_API_WRAPPER_STATIC_VALUE.CORN_STATUS_TRUE为有效标识
                    if (cron.getIsDeleted().equals(0)) {
                        //开启一个新的任务，库中存储的是全类名（包名加类名）通过反射成java类，读取新的时间
                        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule((Runnable) Class.forName(cron.getClassName()).newInstance(), getTrigger(cron));
                        //这一步非常重要，之前直接停用，只停用掉了最后启动的定时任务，前边启用的都没办法停止，所以把每次的对象存到map中可以根据key停用自己想要停用的
                        scheduleMap.put(cron.getId(), future);
                        log.info("=================================>>>>> {} 定时业务已经开始执行====================================", cron.getClassName());
                        cron.setStartTime(DateUtil.date());
                    }
                }
                result = CollUtil.addAllIfNotContains(result, cronTriggers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 设置执行周期
     * @return
     */
    private Trigger getTrigger(TCronTrigger cronTrigger) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 3）从数据库获取执行周期
                String cron = cronTrigger.getCron();
                if (StrUtil.isNotBlank(cron)) {
                    // 4）返回执行周期（Date）
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                } else {
                    return null;
                }
            }
        };
    }
}
