package cn.cncommdata.controller;

import cn.cncommdata.entity.TCronTrigger;
import cn.cncommdata.service.TCronTriggerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TCronTrigger)表控制层
 *
 * @author makejava
 * @since 2020-04-10 14:09:13
 */
@RestController
@RequestMapping("tCronTrigger")
public class TCronTriggerController {
    /**
     * 服务对象
     */
    @Resource
    private TCronTriggerService tCronTriggerService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TCronTrigger selectOne(Long id) {
        return this.tCronTriggerService.queryById(id);
    }

}