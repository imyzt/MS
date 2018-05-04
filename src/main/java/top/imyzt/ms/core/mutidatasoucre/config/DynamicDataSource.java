package top.imyzt.ms.core.mutidatasoucre.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * <p>
 *  动态数据源
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/4 11:50
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
