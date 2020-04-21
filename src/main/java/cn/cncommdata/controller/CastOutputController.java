package cn.cncommdata.controller;

import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.service.CastOutputService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CastOutput)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@RestController
@RequestMapping("castOutput")
public class CastOutputController {
    /**
     * 服务对象
     */
    @Resource
    private CastOutputService castOutputService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CastOutput selectOne(Long id) {
        return this.castOutputService.queryById(id);
    }

}