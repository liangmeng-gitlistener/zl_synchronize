package cn.cncommdata.controller;

import cn.cncommdata.entity.OrderSchedule;
import cn.cncommdata.service.OrderScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OrderSchedule)表控制层
 *
 * @author makejava
 * @since 2020-04-21 10:04:00
 */
@RestController
@RequestMapping("orderSchedule")
public class OrderScheduleController {
    /**
     * 服务对象
     */
    @Resource
    private OrderScheduleService orderScheduleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrderSchedule selectOne(Long id) {
        return this.orderScheduleService.queryById(id);
    }

}