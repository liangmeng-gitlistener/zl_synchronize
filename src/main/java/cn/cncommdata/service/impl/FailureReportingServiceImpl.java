package cn.cncommdata.service.impl;

import cn.cncommdata.entity.FailureReporting;
import cn.cncommdata.dao.FailureReportingDao;
import cn.cncommdata.service.FailureReportingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FailureReporting)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Service("failureReportingService")
public class FailureReportingServiceImpl implements FailureReportingService {
    @Resource
    private FailureReportingDao failureReportingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FailureReporting queryById(Long id) {
        return this.failureReportingDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FailureReporting> queryAllByLimit(int offset, int limit) {
        return this.failureReportingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param failureReporting 实例对象
     * @return 实例对象
     */
    @Override
    public FailureReporting insert(FailureReporting failureReporting) {
        this.failureReportingDao.insert(failureReporting);
        return failureReporting;
    }

    /**
     * 修改数据
     *
     * @param failureReporting 实例对象
     * @return 实例对象
     */
    @Override
    public FailureReporting update(FailureReporting failureReporting) {
        this.failureReportingDao.update(failureReporting);
        return this.queryById(failureReporting.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.failureReportingDao.deleteById(id) > 0;
    }
}