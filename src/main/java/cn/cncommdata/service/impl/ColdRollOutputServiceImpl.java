package cn.cncommdata.service.impl;

import cn.cncommdata.entity.ColdRollOutput;
import cn.cncommdata.dao.ColdRollOutputDao;
import cn.cncommdata.service.ColdRollOutputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ColdRollOutput)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Service("coldRollOutputService")
public class ColdRollOutputServiceImpl implements ColdRollOutputService {
    @Resource
    private ColdRollOutputDao coldRollOutputDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ColdRollOutput queryById(Long id) {
        return this.coldRollOutputDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ColdRollOutput> queryAllByLimit(int offset, int limit) {
        return this.coldRollOutputDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param coldRollOutput 实例对象
     * @return 实例对象
     */
    @Override
    public ColdRollOutput insert(ColdRollOutput coldRollOutput) {
        this.coldRollOutputDao.insert(coldRollOutput);
        return coldRollOutput;
    }

    /**
     * 修改数据
     *
     * @param coldRollOutput 实例对象
     * @return 实例对象
     */
    @Override
    public ColdRollOutput update(ColdRollOutput coldRollOutput) {
        this.coldRollOutputDao.update(coldRollOutput);
        return this.queryById(coldRollOutput.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.coldRollOutputDao.deleteById(id) > 0;
    }
}