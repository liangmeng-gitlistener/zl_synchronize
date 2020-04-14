package cn.cncommdata.dao.mysql;

import cn.cncommdata.entity.TCronTrigger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (TCronTrigger)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 14:09:12
 */
@Mapper
public interface TCronTriggerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TCronTrigger queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCronTrigger> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tCronTrigger 实例对象
     * @return 对象列表
     */
    List<TCronTrigger> queryAll(TCronTrigger tCronTrigger);

    /**
     * 新增数据
     *
     * @param tCronTrigger 实例对象
     * @return 影响行数
     */
    int insert(TCronTrigger tCronTrigger);

    /**
     * 修改数据
     *
     * @param tCronTrigger 实例对象
     * @return 影响行数
     */
    int update(TCronTrigger tCronTrigger);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过主键修改LastRunTime字段
     *
     * @param lastRunTime   上次执行时间
     * @param id            主键
     * @return              影响行数
     */
    int updateLastRunTimeById(Date lastRunTime, Long id);
}