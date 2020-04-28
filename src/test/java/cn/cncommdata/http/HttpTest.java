package cn.cncommdata.http;

import cn.cncommdata.ZlSynchronizeApplicationTests;
import cn.cncommdata.bean.OutputJsonBase;
import cn.cncommdata.bean.ScheduleJsonBase;
import cn.cncommdata.bean.WIPJsonBase;
import cn.cncommdata.config.entity.HttpConfig;
import cn.cncommdata.dao.*;
import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.entity.ColdRollOutput;
import cn.cncommdata.entity.OrderSchedule;
import cn.cncommdata.entity.WipSummary;
import cn.cncommdata.enums.SysConstants;
import cn.cncommdata.runnable.utils.CastOutputUtil;
import cn.cncommdata.runnable.utils.ColdRollOutputUtil;
import cn.cncommdata.runnable.utils.OrderProgressUtil;
import cn.cncommdata.runnable.utils.WIPSummaryUtil;
import cn.cncommdata.utils.ApplicationContextProvider;
import cn.cncommdata.utils.Builder;
import cn.cncommdata.utils.HttpUtils;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
class HttpTest extends ZlSynchronizeApplicationTests {

    @Autowired
    private HttpConfig httpConfig;
    /**
     * 订单进度查询
     */
    @Test
    void OrderProgress(){
        Map<String, Object> paramMap = OrderProgressUtil.getparamMap(httpConfig);
        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.ORDER_PROGRESS.getUrl());
        String result = HttpUtils.get(url, paramMap, httpConfig);

        ScheduleJsonBase bean = JSONUtil.toBean(result, ScheduleJsonBase.class);
        Map<String,Map<String,String>> map = bean.getData().getRows().get(0).getData();
        log.info(map.get("客户").get("value"));
    }

    /**
     * 订单进度查询
     */
    @Test
    void CastOutput(){
        Map<String, Object> paramMap = CastOutputUtil.getParamMap(httpConfig);
        String url = HttpUtils.initURL(httpConfig, SysConstants.RoutingURL.CAST_OUTPUT.getUrl());
        String result = HttpUtils.get(url, paramMap, httpConfig);

        OutputJsonBase bean = JSONUtil.toBean(result, OutputJsonBase.class);
        log.info(bean.toString());
    }

    @Test
    void OrderProgressUtil(){
        List<OrderSchedule> list = OrderProgressUtil.getFromHTTP(httpConfig);
        for (OrderSchedule orderSchedule : list) {
            log.info(orderSchedule.toString());
        }
    }

    @Test
    void CastOutputUtil(){
        List<CastOutput> list = CastOutputUtil.getFromHTTP(httpConfig);
        for (CastOutput castOutput : list) {
            log.info(castOutput.toString());
        }
    }

    @Test
    void WIPSummaryUtil(){
        WIPJsonBase wipJson = WIPSummaryUtil.getFromHTTP(httpConfig);
        List<WipSummary> list = WIPSummaryUtil.getWIPSummaryList(wipJson);
        list.stream().forEach(wipSummary -> log.info(wipSummary.toString()));
    }

    @Test
    void test(){
        OrderScheduleDao orderScheduleDao;
        orderScheduleDao = ApplicationContextProvider.getBean(OrderScheduleDao.class);

        List<OrderSchedule> httpList = OrderProgressUtil.getFromHTTP(httpConfig);
        List<OrderSchedule> dbList = orderScheduleDao.queryAll(null);

        //测试数据
        OrderSchedule o = Builder.of(OrderSchedule::new)
                .with(OrderSchedule::setProcessProductOrder, "test")
                .with(OrderSchedule::setProductOrderNum, "test")
                .with(OrderSchedule::setProductName, "test")
                .with(OrderSchedule::setCustomer, "lm3")
                .with(OrderSchedule::setAlloyState, "test")
                .with(OrderSchedule::setPlanVolume, 100)
                .with(OrderSchedule::setCompletedVolume, 100)
                .build();
        httpList.add(o);
        //测试数据(结束)

        httpList.removeAll(OrderProgressUtil.getSameList(httpList, dbList));
        List<OrderSchedule> needInserts = OrderProgressUtil.needInsert(httpList, dbList);
        //由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。
        httpList.removeAll(needInserts);
        List<OrderSchedule> needUpdates = OrderProgressUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            orderScheduleDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            orderScheduleDao.update(needUpdate);
        });
    }

    @Test
    void test2(){
        CastOutputDao castOutputDao;
        //  此处仅为获取spring中的dao对象（定时任务中无法通过@Autowired来获取对象）
        castOutputDao = ApplicationContextProvider.getBean(CastOutputDao.class);

        List<CastOutput> httpList = CastOutputUtil.getFromHTTP(httpConfig);
        List<CastOutput> dbList = castOutputDao.queryAll(null);
        //  提前过滤http请求与db完全相同的数据（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(CastOutputUtil.getSameList(httpList, dbList));

        List<CastOutput> needInserts = CastOutputUtil.needInsert(httpList, dbList);
        //  由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(needInserts);

        List<CastOutput> needUpdates = CastOutputUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            castOutputDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            castOutputDao.update(needUpdate);
        });
    }

    @Test
    void test3(){
        ColdRollOutputDao coldRollOutputDao;
        //  此处仅为获取spring中的dao对象（定时任务中无法通过@Autowired来获取对象）
        coldRollOutputDao = ApplicationContextProvider.getBean(ColdRollOutputDao.class);

        List<ColdRollOutput> httpList = ColdRollOutputUtil.getFromHTTP(httpConfig);
        List<ColdRollOutput> dbList = coldRollOutputDao.queryAll(null);
        //  提前过滤http请求与db完全相同的数据（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(ColdRollOutputUtil.getSameList(httpList, dbList));

        List<ColdRollOutput> needInserts = ColdRollOutputUtil.needInsert(httpList, dbList);
        //  由于实际需要更新的数据一定不是需要插入的数据，此处为提高性能而做的处理。（此处会改变httpList的值，代码顺序不能调整）
        httpList.removeAll(needInserts);

        List<ColdRollOutput> needUpdates = ColdRollOutputUtil.needUpdate(httpList, dbList);

        needInserts.stream().forEach( (needInsert) -> {
            coldRollOutputDao.insert(needInsert);
        });

        needUpdates.stream().forEach( (needUpdate) -> {
            coldRollOutputDao.update(needUpdate);
        });
    }

    @Test
    void test4(){
        WipSummaryDao wipSummaryDao;
        wipSummaryDao = ApplicationContextProvider.getBean(WipSummaryDao.class);

        WIPJsonBase wipJson = WIPSummaryUtil.getFromHTTP(httpConfig);
        List<WipSummary> httpList = WIPSummaryUtil.getWIPSummaryList(wipJson);

        for (WipSummary http : httpList) {
            if (http.getWipName().equals("double_foil_blank")) {
                http.setVolume(2);
            }
        }

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
    }

    @Test
    void myTest() {
        Map<String, Object> result = CastOutputUtil.getParamMap(httpConfig);
        log.info(result.toString());
    }
}
