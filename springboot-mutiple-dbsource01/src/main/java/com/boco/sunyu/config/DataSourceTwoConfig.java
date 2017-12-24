package com.boco.sunyu.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceTwoConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplateTwo") // 扫描dao或mapper接口
public class DataSourceTwoConfig extends AbstractDataSourceConfig {

    static final String PACKAGE = "com.boco.sunyu.dao.two";
    static final String MAPPER_LOCATION = "classpath:/mapper/two/*Dao.xml";
    @Bean(name = "dataSourceTwo")
    public DataSource dataSourceTest(Environment env,DataSourceTwoProperties dataSourceTwoProperties){
       // String prefix = "spring.datasource.druid.two.";
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dataSourceTwoProperties,dataSource);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSource(dataSource);
        ds.setUniqueResourceName("two");
        return ds;
    }

    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTest(@Qualifier("dataSourceTwo") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.boco.sunyu.model");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplateTest(
            @Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
