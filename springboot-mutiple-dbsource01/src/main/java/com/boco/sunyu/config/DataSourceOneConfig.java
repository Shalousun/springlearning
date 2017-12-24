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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.ibatis.plugin.Interceptor;
import com.github.pagehelper.PageHelper;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages =DataSourceOneConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplateOne") // 扫描dao或mapper接口
public class DataSourceOneConfig extends AbstractDataSourceConfig{

    static final String PACKAGE = "com.boco.sunyu.dao.one";

    static final String MAPPER_LOCATION = "classpath:/mapper/one/*Dao.xml";

    static final String CONFIG_LOCATION = "/mybatis-config.xml";
    @Primary
    @Bean(name = "dataSourceOne")
    public DataSource dataSourceOne(Environment env,DataSourceOneProperties dataSourceOneProperties) throws SQLException{
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dataSourceOneProperties,dataSource);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSource(dataSource);
        ds.setUniqueResourceName("one");
      return ds;
    }

    @Bean(name = "sqlSessionFactoryOne")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTypeAliasesPackage("com.boco.sunyu.model");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        bean.setConfigLocation(new DefaultResourceLoader().getResource(CONFIG_LOCATION));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateOne")
    public SqlSessionTemplate sqlSessionTemplateCar(
            @Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
