package cn.cncommdata.service;

import cn.cncommdata.entity.CastOutput;
import java.util.List;

/**
 * (CastOutput)表服务接口
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
public interface CastOutputService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CastOutput queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CastOutput> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param castOutput 实例对象
     * @return 实例对象
     */
    CastOutput insert(CastOutput castOutput);

    /**
     * 修改数据
     *
     * @param castOutput 实例对象
     * @return 实例对象
     */
    CastOutput update(CastOutput castOutput);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}