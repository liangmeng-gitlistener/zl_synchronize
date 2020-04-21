package cn.cncommdata.controller;

import cn.cncommdata.entity.ColdRollOutput;
import cn.cncommdata.service.ColdRollOutputService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ColdRollOutput)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@RestController
@RequestMapping("coldRollOutput")
public class ColdRollOutputController {
    /**
     * 服务对象
     */
    @Resource
    private ColdRollOutputService coldRollOutputService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ColdRollOutput selectOne(Long id) {
        return this.coldRollOutputService.queryById(id);
    }

}