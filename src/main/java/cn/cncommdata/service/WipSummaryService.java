package cn.cncommdata.service;

import cn.cncommdata.entity.WipSummary;
import java.util.List;

/**
 * (WipSummary)表服务接口
 *
 * @author makejava
 * @since 2020-04-23 10:18:59
 */
public interface WipSummaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WipSummary queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WipSummary> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param wipSummary 实例对象
     * @return 实例对象
     */
    WipSummary insert(WipSummary wipSummary);

    /**
     * 修改数据
     *
     * @param wipSummary 实例对象
     * @return 实例对象
     */
    WipSummary update(WipSummary wipSummary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}