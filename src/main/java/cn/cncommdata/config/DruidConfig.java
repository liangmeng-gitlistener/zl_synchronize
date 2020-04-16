package cn.cncommdata.config;

import cn.cncommdata.config.entity.DruidLoginConfig;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 测试页面地址
 *  http://localhost:8080/druid/sql.html
 */
@Configuration
public class DruidConfig {

    @Autowired
    private DruidLoginConfig druidLoginConfig;

    /**
     * 主要实现web监控的配置处理
     * @return
     */
    @Bean(name = "default_datadatasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        // 用户名
        initParameters.put("loginUsername", Optional.ofNullable(druidLoginConfig.getUserName()).orElse("root"));
        // 密码
        initParameters.put("loginPassword", Optional.ofNullable(druidLoginConfig.getPassword()).orElse("root"));
        // 禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("allow", Optional.ofNullable(druidLoginConfig.getAllow()).orElse(""));
        // IP黑名单 (存在共同时，deny优先于allow)
        //initParameters.put("deny", "192.168.20.38");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //  可以将配置设置为禁用状态。
        filterRegistrationBean.setEnabled(Optional.ofNullable(druidLoginConfig.getEnable()).orElse(false));
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
