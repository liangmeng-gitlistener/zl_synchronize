package cn.cncommdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.cncommdata.dao.mysql")
@EnableScheduling
public class ZlSynchronizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlSynchronizeApplication.class, args);
    }

}
