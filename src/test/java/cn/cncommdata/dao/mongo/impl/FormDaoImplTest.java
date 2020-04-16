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
        Long formId = 1240468533985546242L;
        List<FormData> formDatas = formDao.getFormDataListByFormId(formId);
        for (FormData formdata : formDatas) {
            log.info(formdata.toString());
        }
    }

    @Test
    void getByFormDataId() {
        FormData formData = formDao.getByFormDataId(1240468533985546242L, 1218734876489027584L);
        //强行装逼，没必要.用一下java8的新特性
        formData = Optional.ofNullable(formData).orElse(new FormData());
        log.info("haha: {}", formData.toString());
    }
}