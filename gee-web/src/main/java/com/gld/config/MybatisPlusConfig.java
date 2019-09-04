package com.gld.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: Mybatis-puls配置类
 * Created by qiuzx on 2019-09-03.
 * @Version 1.0
 * @Author qiuzx@gld.com
 */
@Configuration
@MapperScan("com.gld.mappers")
public class MybatisPlusConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        //注册Map 下划线转驼峰
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());

        sqlSessionFactory.setDataSource(dataSource);

        sqlSessionFactory.setConfiguration(configuration);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        sqlSessionFactory.setPlugins(new Interceptor[]{paginationInterceptor});
        return sqlSessionFactory.getObject();
    }
}
