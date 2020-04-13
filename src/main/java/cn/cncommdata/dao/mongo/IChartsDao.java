package cn.cncommdata.dao.mongo;

import cn.cncommdata.form.model.FormData;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * IChartsDao
 *
 * @author: weihong.zhu
 * @date: 2019-09-24
 */
public interface IChartsDao {

    /**
     * 根据条件查询formData数据
     *
     * @param query            查询条件
     * @return List<FormData>
     */
    List<FormData> getFormDataList(Query query);
}
