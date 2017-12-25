package com.boco.sunyu.config;

import com.boco.sunyu.datasource.CustomSqlSessionTemplate;
import com.boco.sunyu.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages =MyBatisConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig extends AbstractDataSourceConfig {

    static final String PACKAGE = "com.boco.sunyu.dao";

    static final String MAPPER_LOCATION = "classpath:/mapper/*Dao.xml";

    @Primary
    @Bean(name = "dataSourceOne")
    public DataSource dataSourceOne(Environment env) {
        String prefix = "spring.datasource.one.";
        return getDataSource(env,prefix,"one");
    }

    @Bean(name = "dataSourceTwo")
    public DataSource dataSourceTwo(Environment env){
        String prefix = "spring.datasource.two.";
        return getDataSource(env,prefix,"two");
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("dataSourceOne") DataSource masterDataSource,
                                               @Qualifier("dataSourceTwo") DataSource slaverDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("one", masterDataSource);
        targetDataSources.put("two", slaverDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }
    @Bean(name = "sqlSessionFactoryOne")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }
    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTest(@Qualifier("dataSourceTwo") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }


//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryTest(@Qualifier("dynamicDataSource") DynamicDataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("com.boco.sunyu.model");
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return bean.getObject();
//    }

    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplateTest(
            @Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactoryOne,
            @Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactoryTwo) throws Exception {
        Map<Object,SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put("one",sqlSessionFactoryOne);
        sqlSessionFactoryMap.put("two",sqlSessionFactoryTwo);
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(sqlSessionFactoryOne);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }

    /**
     * 创建数据源
     * @param dataSource
     * @return
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.boco.sunyu.model");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
}
