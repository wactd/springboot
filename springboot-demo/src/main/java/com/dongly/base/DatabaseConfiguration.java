package com.dongly.base;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by dongly on 2017/5/23.
 */

@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.dongly")
public class DatabaseConfiguration implements EnvironmentAware {

    private Environment environment;

    @Autowired
    private DataSourceModel dataSourceModel;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 注册数据源
     *
     * @author dongly
     * @version 1.0
     * @date 2017/5/23 20:01
     */
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
        druidDataSource.setFilters(dataSourceModel.getFilters());
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com.dongly/**/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

}
