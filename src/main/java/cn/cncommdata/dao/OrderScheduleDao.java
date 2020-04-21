package cn.cncommdata.dao;

import cn.cncommdata.entity.OrderSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (OrderSchedule)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 10:04:00
 */
@Mapper
public interface OrderScheduleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderSchedule queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderSchedule> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderSchedule 实例对象
     * @return 对象列表
     */
    List<OrderSchedule> queryAll(OrderSchedule orderSchedule);

    /**
     * 新增数据
     *
     * @param orderSchedule 实例对象
     * @return 影响行数
     */
    int insert(OrderSchedule orderSchedule);

    /**
     * 修改数据
     *
     * @param orderSchedule 实例对象
     * @return 影响行数
     */
    int update(OrderSchedule orderSchedule);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}