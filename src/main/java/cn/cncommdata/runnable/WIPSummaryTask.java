package cn.cncommdata.runnable;

import cn.cncommdata.bean.WIPJsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.dao.WipSummaryDao;
import cn.cncommdata.entity.WipSummary;
import cn.cncommdata.enums.TaskEnum;
import cn.cncommdata.runnable.utils.RunnableUtil;
import cn.cncommdata.runnable.utils.WIPSummaryUtil;
import cn.cncommdata.utils.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WIPSummaryTask implements Runnable {
    private TCronTriggerDao cronTriggerDao;
    private WipSummaryDao wipSummaryDao;
    private HttpConfig httpConfig;

    @Override
    public void run() {
        //  此处仅为获取spring中的dao对象（定时任务中无法通过@Autowired来获取对象）
        this.httpConfig = ApplicationContextProvider.getBean(HttpConfig.class);
        this.cronTriggerDao = ApplicationContextProvider.getBean(TCronTriggerDao.class);
        this.wipSummaryDao = ApplicationContextProvider.getBean(WipSummaryDao.class);

        WIPJsonBase wipJson = WIPSummaryUtil.getFromHTTP(httpConfig);
        List<WipSummary> httpList = WIPSummaryUtil.getWIPSummaryList(wipJson);

        //  数据库数据量非常少，暂时使用此种方法
        List<WipSummary> dbList = wipSummaryDao.queryAll(null);

        List<WipSummary> needInserts = WIPSummaryUtil.needInsert(httpList, dbList);
        //  由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(needInserts);

        List<WipSummary> needUpdates = WIPSummaryUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            wipSummaryDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            wipSummaryDao.update(needUpdate);
        });

        //  反写数据库执行时间
        RunnableUtil.UpdateLastRunTime(cronTriggerDao, TaskEnum.TEST_TASK.getTaskId());
    }
}