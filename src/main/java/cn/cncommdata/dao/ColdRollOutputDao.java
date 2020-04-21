package cn.cncommdata.dao;

import cn.cncommdata.entity.ColdRollOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ColdRollOutput)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Mapper
public interface ColdRollOutputDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ColdRollOutput queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ColdRollOutput> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param coldRollOutput 实例对象
     * @return 对象列表
     */
    List<ColdRollOutput> queryAll(ColdRollOutput coldRollOutput);

    /**
     * 新增数据
     *
     * @param coldRollOutput 实例对象
     * @return 影响行数
     */
    int insert(ColdRollOutput coldRollOutput);

    /**
     * 修改数据
     *
     * @param coldRollOutput 实例对象
     * @return 影响行数
     */
    int update(ColdRollOutput coldRollOutput);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}