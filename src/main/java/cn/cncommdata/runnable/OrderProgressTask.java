package cn.cncommdata.runnable;

import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.dao.OrderScheduleDao;
import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.entity.OrderSchedule;
import cn.cncommdata.enums.TaskEnum;
import cn.cncommdata.runnable.utils.OrderProgressUtil;
import cn.cncommdata.runnable.utils.RunnableUtil;
import cn.cncommdata.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderProgressTask implements Runnable {
    private TCronTriggerDao cronTriggerDao;
    private OrderScheduleDao orderScheduleDao;
    private HttpConfig httpConfig;

    @Override
    public void run() {
        //  此处仅为获取spring中的dao对象（定时任务中无法通过@Autowired来获取对象）
        this.httpConfig = ApplicationContextProvider.getBean(HttpConfig.class);
        this.cronTriggerDao = ApplicationContextProvider.getBean(TCronTriggerDao.class);
        this.orderScheduleDao = ApplicationContextProvider.getBean(OrderScheduleDao.class);

        List<OrderSchedule> httpList = OrderProgressUtil.getFromHTTP(httpConfig);
        List<OrderSchedule> dbList = orderScheduleDao.queryAll(null);
        //  提前过滤http请求与db完全相同的数据（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(OrderProgressUtil.getSameList(httpList, dbList));

        List<OrderSchedule> needInserts = OrderProgressUtil.needInsert(httpList, dbList);
        //  由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(needInserts);

        List<OrderSchedule> needUpdates = OrderProgressUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            orderScheduleDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            orderScheduleDao.update(needUpdate);
        });

        //  反写数据库执行时间
        RunnableUtil.UpdateLastRunTime(cronTriggerDao, TaskEnum.TEST_TASK.getTaskId());
    }
}