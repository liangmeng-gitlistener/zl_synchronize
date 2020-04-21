package cn.cncommdata.service.impl;

import cn.cncommdata.entity.CastOutput;
import cn.cncommdata.dao.CastOutputDao;
import cn.cncommdata.service.CastOutputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CastOutput)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Service("castOutputService")
public class CastOutputServiceImpl implements CastOutputService {
    @Resource
    private CastOutputDao castOutputDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CastOutput queryById(Long id) {
        return this.castOutputDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CastOutput> queryAllByLimit(int offset, int limit) {
        return this.castOutputDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param castOutput 实例对象
     * @return 实例对象
     */
    @Override
    public CastOutput insert(CastOutput castOutput) {
        this.castOutputDao.insert(castOutput);
        return castOutput;
    }

    /**
     * 修改数据
     *
     * @param castOutput 实例对象
     * @return 实例对象
     */
    @Override
    public CastOutput update(CastOutput castOutput) {
        this.castOutputDao.update(castOutput);
        return this.queryById(castOutput.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.castOutputDao.deleteById(id) > 0;
    }
}