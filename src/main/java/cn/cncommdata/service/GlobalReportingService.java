package cn.cncommdata.service;

import cn.cncommdata.entity.GlobalReporting;
import java.util.List;

/**
 * (GlobalReporting)表服务接口
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
public interface GlobalReportingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GlobalReporting queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GlobalReporting> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param globalReporting 实例对象
     * @return 实例对象
     */
    GlobalReporting insert(GlobalReporting globalReporting);

    /**
     * 修改数据
     *
     * @param globalReporting 实例对象
     * @return 实例对象
     */
    GlobalReporting update(GlobalReporting globalReporting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}