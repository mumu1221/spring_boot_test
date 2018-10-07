package cn.mucfc.com.checkModel.dynamicdatasource.configuration;

import cn.mucfc.com.checkModel.dynamicdatasource.common.DataSourceKey;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Multiple DataSource Configurer
 *
 * @author HelloWood
 * @date 2017 -08-15 11:37
 * @Email hellowoodes @gmail.com
 */
@Configuration
public class DataSourceConfigurer {

    /**
     * defaultLocal DataSource
     *
     * @return data source
     */
    @Bean("defaultlocal")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.defaultlocal")
    public DataSource defaultLocal() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave alpha data source.
     *
     * @return the data source
     */
    @Bean("cifmysql")
    @ConfigurationProperties(prefix = "spring.datasource.druid.cifmysql")
    public DataSource cifmysql() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave beta data source.
     *
     * @return the data source
     */
    @Bean("cifvertica")
    @ConfigurationProperties(prefix = "spring.datasource.druid.cifvertica")
    public DataSource cifvertica() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave gamma data source.
     *
     * @return the data source
     */
    @Bean("cifimpala")
    @ConfigurationProperties(prefix = "spring.datasource.druid.cifimpala")
    public DataSource cifimpala() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(10);
        dataSourceMap.put(DataSourceKey.defaultLocal.name(), defaultLocal());
        dataSourceMap.put(DataSourceKey.cifmysql.name(),cifmysql());
        dataSourceMap.put(DataSourceKey.cifvertica.name(), cifvertica());
        dataSourceMap.put(DataSourceKey.cifimpala.name(), cifimpala());

        // Set defaultLocal datasource as default
        dynamicRoutingDataSource.setDefaultTargetDataSource(defaultLocal());
        // Set defaultLocal and slave datasource as target datasource
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // To put datasource keys into DataSourceContextHolder to judge if the datasource is exist
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        return dynamicRoutingDataSource;
    }


    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}

