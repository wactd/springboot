package com.dongly.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by tiger on 17-5-24.
 */

@Configuration
// mybatis扫描配sql文件
@MapperScan(value = "com.dongly.**.mapper")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * mybatis 配置路径
     */
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    /**
     * mybatis mapper resource 路径
     */
    private static String MAPPER_PATH = "/com.dongly/**/*Mapper.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径 */
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /** 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        bean.setMapperLocations(resolver.getResources(packageSearchPath));
        /** 设置datasource */
        bean.setDataSource(dataSource);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public PageHelper pageHelper() {
        System.out.println("====>>>开始加载分页信息====>>>");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.put("reasonable", "true");
        properties.put("helperDialect", "mysql");
        properties.put("supportMethodsArguments", "true");
        properties.put("params", "count=countSql");
        properties.put("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}