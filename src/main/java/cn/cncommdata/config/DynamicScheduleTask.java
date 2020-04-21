package cn.cncommdata.config;

import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.entity.TCronTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DynamicScheduleTask implements CommandLineRunner {

    @Autowired
    ScheduleConfig scheduleConfig;
    @Autowired
    private TCronTriggerDao cronTriggerDao;

    @Override
    public void run(String... args){
        //从数据库获取定时任务列表数据
        List<TCronTrigger> cronTriggers = cronTriggerDao.queryAll(null);
        List<TCronTrigger> dataCronTriggers= scheduleConfig.startCron(cronTriggers);
        //修改数据库数据
        dataCronTriggers.forEach(cronTriggerDao::update);
    }
}