package cn.cncommdata.config;
import	java.util.ArrayList;

import cn.cncommdata.dao.mysql.TCronTriggerDao;
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
    public void run(String... args) throws Exception {
        List<TCronTrigger> cronTriggers = cronTriggerDao.queryAll(null);
        List<TCronTrigger> dataCronTriggers= scheduleConfig.startCron(cronTriggers);
        dataCronTriggers.forEach(cronTriggerDao::update);
    }
}