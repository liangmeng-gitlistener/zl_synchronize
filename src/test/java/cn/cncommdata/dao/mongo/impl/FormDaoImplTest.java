package cn.cncommdata.dao.mongo.impl;

import cn.cncommdata.ZlSynchronizeApplicationTests;
import cn.cncommdata.dao.mongo.IFormDao;
import cn.cncommdata.form.model.FormData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class FormDaoImplTest extends ZlSynchronizeApplicationTests {
    @Autowired
    private IFormDao formDao;

    @Test
    void getFormDataListByFormId() {
        Long formId = 1240215509488766986L;
        List<FormData> formDatas = formDao.getFormDataListByFormId(formId);
        for (FormData formdata : formDatas) {
            log.info(formdata.toString());
        }
    }

    @Test
    void getByFormDataId() {
        FormData formData = formDao.getByFormDataId(1247403865356242944L, 1218734876489027584L);
        //强行装逼，没必要
        formData = Optional.ofNullable(formData).orElseGet(null);
        log.info("haha: {}", formData.toString());
    }
}