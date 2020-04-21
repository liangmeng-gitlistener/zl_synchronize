package cn.cncommdata.service.impl;

import cn.cncommdata.entity.OrderSchedule;
import cn.cncommdata.dao.OrderScheduleDao;
import cn.cncommdata.service.OrderScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderSchedule)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 10:04:00
 */
@Service("orderScheduleService")
public class OrderScheduleServiceImpl implements OrderScheduleService {
    @Resource
    private OrderScheduleDao orderScheduleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderSchedule queryById(Long id) {
        return this.orderScheduleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderSchedule> queryAllByLimit(int offset, int limit) {
        return this.orderScheduleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderSchedule 实例对象
     * @return 实例对象
     */
    @Override
    public OrderSchedule insert(OrderSchedule orderSchedule) {
        this.orderScheduleDao.insert(orderSchedule);
        return orderSchedule;
    }

    /**
     * 修改数据
     *
     * @param orderSchedule 实例对象
     * @return 实例对象
     */
    @Override
    public OrderSchedule update(OrderSchedule orderSchedule) {
        this.orderScheduleDao.update(orderSchedule);
        return this.queryById(orderSchedule.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderScheduleDao.deleteById(id) > 0;
    }
}