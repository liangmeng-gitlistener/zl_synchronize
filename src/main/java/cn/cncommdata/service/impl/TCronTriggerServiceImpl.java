package cn.cncommdata.service.impl;

import cn.cncommdata.entity.TCronTrigger;
import cn.cncommdata.dao.TCronTriggerDao;
import cn.cncommdata.service.TCronTriggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCronTrigger)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 14:09:12
 */
@Service("tCronTriggerService")
public class TCronTriggerServiceImpl implements TCronTriggerService {
    @Resource
    private TCronTriggerDao tCronTriggerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TCronTrigger queryById(Long id) {
        return this.tCronTriggerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TCronTrigger> queryAllByLimit(int offset, int limit) {
        return this.tCronTriggerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tCronTrigger 实例对象
     * @return 实例对象
     */
    @Override
    public TCronTrigger insert(TCronTrigger tCronTrigger) {
        this.tCronTriggerDao.insert(tCronTrigger);
        return tCronTrigger;
    }

    /**
     * 修改数据
     *
     * @param tCronTrigger 实例对象
     * @return 实例对象
     */
    @Override
    public TCronTrigger update(TCronTrigger tCronTrigger) {
        this.tCronTriggerDao.update(tCronTrigger);
        return this.queryById(tCronTrigger.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tCronTriggerDao.deleteById(id) > 0;
    }
}