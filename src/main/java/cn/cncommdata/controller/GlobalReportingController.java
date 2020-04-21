package cn.cncommdata.controller;

import cn.cncommdata.entity.GlobalReporting;
import cn.cncommdata.service.GlobalReportingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GlobalReporting)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@RestController
@RequestMapping("globalReporting")
public class GlobalReportingController {
    /**
     * 服务对象
     */
    @Resource
    private GlobalReportingService globalReportingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GlobalReporting selectOne(Long id) {
        return this.globalReportingService.queryById(id);
    }

}