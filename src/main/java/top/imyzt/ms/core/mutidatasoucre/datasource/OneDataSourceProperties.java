package top.imyzt.ms.core.mutidatasoucre.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * <p>
 *     测试第一个数据源
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018年5月4日13:10
 */
@Component
@ConfigurationProperties(prefix = OneDataSourceProperties.DATASOURCE)
public class OneDataSourceProperties {

    public static final String DATASOURCE = "ms.muti-datasource.slave.one";

    /**
     * 第二个数据源的链接
     */
    @Value("${url}")
    private String url;

    /**
     * 第二个数据源的数据库账号
     */
    @Value("${username}")
    private String username;

    /**
     * 第二个数据源的数据库密码
     */
    @Value("${password}")
    private String password;

    /**
     * 配置数据库的url,uname,pwd
     * @param dataSource 数据源
     */
    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
