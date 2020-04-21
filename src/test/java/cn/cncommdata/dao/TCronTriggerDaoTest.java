package cn.cncommdata.dao;

import cn.cncommdata.ZlSynchronizeApplicationTests;
import cn.cncommdata.entity.TCronTrigger;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
@EnableAutoConfiguration
class TCronTriggerDaoTest extends ZlSynchronizeApplicationTests {

    @Autowired
    private TCronTriggerDao tCronTriggerDao;

    @Test
    void queryAll() {
        TCronTrigger tCronTrigger = null;
        List<TCronTrigger> list = tCronTriggerDao.queryAll(tCronTrigger);
        for (TCronTrigger trigger : list) {
            log.info(trigger.toString());
        }
    }

    @Test
    void queryById() {
        Long id = 1L;
        TCronTrigger tCronTrigger = tCronTriggerDao.queryById(id);
        log.info(tCronTrigger.toString());
    }

    @Test
    void updateLastRunTimeById(){
        String test = "2017-03-01 22:33:23";
        Date lastRunTime = DateUtil.parse(test);
        tCronTriggerDao.updateLastRunTimeById(lastRunTime, 1L);
    }
}