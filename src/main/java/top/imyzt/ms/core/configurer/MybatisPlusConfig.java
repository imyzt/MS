package top.imyzt.ms.core.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.imyzt.ms.common.enumerate.DSEnum;
import top.imyzt.ms.core.mutidatasoucre.config.DruidProperties;
import top.imyzt.ms.core.mutidatasoucre.config.DynamicDataSource;
import top.imyzt.ms.core.mutidatasoucre.datasource.MutiDataSourceProperties;
import top.imyzt.ms.core.mutidatasoucre.datasource.OneDataSourceProperties;

import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * <p>
 *     mybatis-plus配置文件
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:41
 */
@EnableTransactionManagement
@Configuration                  //标识此类为配置文件类
@MapperScan(basePackages = {"top.imyzt.ms.modular.*.mapper"}) //mybatis-plus扫描Mapper.java
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;
    @Autowired
    OneDataSourceProperties oneDataSourceProperties;

    /**--------------------------------------数据源----------------------------------------*/

    /**
     * 默认的数据源
     */
    private DruidDataSource dataSourceMs(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 其它数据源
     * @return
     */
    private DruidDataSource dataSourceOne(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        oneDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**--------------------------------------数据源----------------------------------------*/


    /**
     * 单数据源连接池配置
     *
     * ConditionalOnProperty注解,当指定的yml配置文件的值符合havingValue的值时,配置文件生效
     */
    @Bean
    @ConditionalOnProperty(prefix = "ms", name = "muti-datasource.muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceMs();
    }

    /**
     * 多数据源连接池配置
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "ms", name = "muti-datasource.muti-datasource-open", havingValue = "true")
    public DynamicDataSource dynamicDataSource(){

        DruidDataSource dataSourceMs = dataSourceMs();
        DruidDataSource dataSourceOne = dataSourceOne();

        try {
            dataSourceMs.init();
            dataSourceOne.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> dataSourceMap = new HashMap<>();

        //在使用@DS注解时切换数据源,依靠此处的key-value对应关系
        dataSourceMap.put(DSEnum.DATA_SOURCE_MS, dataSourceMs);
        dataSourceMap.put(DSEnum.DATA_SOURCE_ONE, dataSourceOne);

        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMs);
        return dynamicDataSource;
    }


    /**
     * 分页插件,自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
