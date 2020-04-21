package cn.cncommdata.service;

import cn.cncommdata.entity.OrderSchedule;
import java.util.List;

/**
 * (OrderSchedule)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 10:04:00
 */
public interface OrderScheduleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderSchedule queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderSchedule> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderSchedule 实例对象
     * @return 实例对象
     */
    OrderSchedule insert(OrderSchedule orderSchedule);

    /**
     * 修改数据
     *
     * @param orderSchedule 实例对象
     * @return 实例对象
     */
    OrderSchedule update(OrderSchedule orderSchedule);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}