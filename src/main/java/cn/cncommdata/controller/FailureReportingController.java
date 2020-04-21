package cn.cncommdata.controller;

import cn.cncommdata.entity.FailureReporting;
import cn.cncommdata.service.FailureReportingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FailureReporting)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@RestController
@RequestMapping("failureReporting")
public class FailureReportingController {
    /**
     * 服务对象
     */
    @Resource
    private FailureReportingService failureReportingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FailureReporting selectOne(Long id) {
        return this.failureReportingService.queryById(id);
    }

}