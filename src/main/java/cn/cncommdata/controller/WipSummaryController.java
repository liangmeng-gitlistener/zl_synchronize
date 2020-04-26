package cn.cncommdata.controller;

import cn.cncommdata.entity.WipSummary;
import cn.cncommdata.service.WipSummaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (WipSummary)表控制层
 *
 * @author makejava
 * @since 2020-04-23 10:18:59
 */
@RestController
@RequestMapping("wipSummary")
public class WipSummaryController {
    /**
     * 服务对象
     */
    @Resource
    private WipSummaryService wipSummaryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public WipSummary selectOne(Long id) {
        return this.wipSummaryService.queryById(id);
    }
}