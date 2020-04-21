package cn.cncommdata.dao;

import cn.cncommdata.entity.GlobalReporting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GlobalReporting)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Mapper
public interface GlobalReportingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GlobalReporting queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GlobalReporting> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param globalReporting 实例对象
     * @return 对象列表
     */
    List<GlobalReporting> queryAll(GlobalReporting globalReporting);

    /**
     * 新增数据
     *
     * @param globalReporting 实例对象
     * @return 影响行数
     */
    int insert(GlobalReporting globalReporting);

    /**
     * 修改数据
     *
     * @param globalReporting 实例对象
     * @return 影响行数
     */
    int update(GlobalReporting globalReporting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}