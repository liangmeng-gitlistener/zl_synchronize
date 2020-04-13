package cn.cncommdata.dao.mongo.impl;

import cn.cncommdata.dao.mongo.IChartsDao;
import cn.cncommdata.form.model.FormData;
import cn.cncommdata.form.util.constant.Const;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * IChartsDaoImpl
 *
 * @author: weihong.zhu
 * @date: 2019-09-24
 */
@Component
public class ChartsDaoImpl implements IChartsDao {

    /**
     * 注入MongoTemplate
     */
    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 根据条件查询formData数据
     *
     * @param query            查询条件
     * @return
     */
    @Override
    public List<FormData> getFormDataList(Query query) {
        return mongoTemplate.find(query, FormData.class, Const.COLLECTION_FORM_DATA);
    }
}
