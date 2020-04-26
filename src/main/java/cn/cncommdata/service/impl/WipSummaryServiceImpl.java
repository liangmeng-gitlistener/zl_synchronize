package cn.cncommdata.service.impl;

import cn.cncommdata.entity.WipSummary;
import cn.cncommdata.dao.WipSummaryDao;
import cn.cncommdata.service.WipSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WipSummary)表服务实现类
 *
 * @author makejava
 * @since 2020-04-23 10:18:59
 */
@Service("wipSummaryService")
public class WipSummaryServiceImpl implements WipSummaryService {
    @Resource
    private WipSummaryDao wipSummaryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WipSummary queryById(Long id) {
        return this.wipSummaryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WipSummary> queryAllByLimit(int offset, int limit) {
        return this.wipSummaryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param wipSummary 实例对象
     * @return 实例对象
     */
    @Override
    public WipSummary insert(WipSummary wipSummary) {
        this.wipSummaryDao.insert(wipSummary);
        return wipSummary;
    }

    /**
     * 修改数据
     *
     * @param wipSummary 实例对象
     * @return 实例对象
     */
    @Override
    public WipSummary update(WipSummary wipSummary) {
        this.wipSummaryDao.update(wipSummary);
        return this.queryById(wipSummary.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.wipSummaryDao.deleteById(id) > 0;
    }
}