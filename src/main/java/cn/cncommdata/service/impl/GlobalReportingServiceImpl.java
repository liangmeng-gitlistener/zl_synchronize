package cn.cncommdata.service.impl;

import cn.cncommdata.entity.GlobalReporting;
import cn.cncommdata.dao.GlobalReportingDao;
import cn.cncommdata.service.GlobalReportingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GlobalReporting)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:22:54
 */
@Service("globalReportingService")
public class GlobalReportingServiceImpl implements GlobalReportingService {
    @Resource
    private GlobalReportingDao globalReportingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GlobalReporting queryById(Long id) {
        return this.globalReportingDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GlobalReporting> queryAllByLimit(int offset, int limit) {
        return this.globalReportingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param globalReporting 实例对象
     * @return 实例对象
     */
    @Override
    public GlobalReporting insert(GlobalReporting globalReporting) {
        this.globalReportingDao.insert(globalReporting);
        return globalReporting;
    }

    /**
     * 修改数据
     *
     * @param globalReporting 实例对象
     * @return 实例对象
     */
    @Override
    public GlobalReporting update(GlobalReporting globalReporting) {
        this.globalReportingDao.update(globalReporting);
        return this.queryById(globalReporting.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.globalReportingDao.deleteById(id) > 0;
    }
}