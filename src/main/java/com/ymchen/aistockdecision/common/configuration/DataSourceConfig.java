package com.ymchen.aistockdecision.common.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dsProperties;

    @Bean
    public DataSource dataSource(){
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        DruidDataSource ds0 = new DruidDataSource();
        ds0.setUrl(dsProperties.getUrl());
        ds0.setUsername(dsProperties.getUsername());
        ds0.setPassword(dsProperties.getPassword());
        ds0.setTestOnBorrow(dsProperties.getTestOnBorrow());
        ds0.setTestOnReturn(dsProperties.getTestOnReturn());
        ds0.setTestWhileIdle(dsProperties.getTestWhileIdle());
        ds0.setTimeBetweenEvictionRunsMillis(dsProperties.getTimeBetweenEvictionRunsMillis());
        ds0.setMaxActive(dsProperties.getMaxActive());
        ds0.setInitialSize(dsProperties.getInitialSize());
        dataSourceMap.put("ds0",ds0);

        // 配置t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new
                ShardingTableRuleConfiguration("t_order", "ds0.t_order_${0..1}");
         // 配置分库策略
        //orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
        // 配置分⽚规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        //Properties dbShardingAlgorithmrProps = new Properties();
        //dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
        //shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${order_id% 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        Properties snowflakeProperties = new Properties();
        snowflakeProperties.setProperty("worker-id", "123");
        shardingRuleConfig.getKeyGenerators().put("snowflake", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", snowflakeProperties));

        try {
            DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new
                            Properties());
            return  dataSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
