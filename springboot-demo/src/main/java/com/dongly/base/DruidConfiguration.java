package com.dongly.base;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid相关配置
 */
@Configuration
public class DruidConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSourceModel dataSourceModel;

    /**
     * 注册数据源
     *
     * @author dongly
     * @version 1.0
     * @date 2017/5/23 20:01
     */
    @PostConstruct
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceModel.getDriverClassName());
        druidDataSource.setUrl(dataSourceModel.getUrl());
        druidDataSource.setUsername(dataSourceModel.getUsername());
        druidDataSource.setPassword(dataSourceModel.getPassword());
        druidDataSource.setInitialSize(dataSourceModel.getInitialSize());
        druidDataSource.setMinIdle(dataSourceModel.getMinIdle());
        druidDataSource.setMaxActive(dataSourceModel.getMaxActive());
        druidDataSource.setMaxWait(dataSourceModel.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceModel.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceModel.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceModel.getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceModel.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceModel.getTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceModel.getTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceModel.getPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceModel.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            druidDataSource.setFilters(dataSourceModel.getFilters());
        } catch (SQLException e) {
            logger.error("======>>> Druid configuration initialization filter Failure!!!", e);
        }
        return druidDataSource;
    }


    /**
     * 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServle2() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：WebStatFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
