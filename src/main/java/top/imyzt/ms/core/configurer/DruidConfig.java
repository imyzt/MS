package top.imyzt.ms.core.configurer;

import cn.hutool.core.lang.Console;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <p>
 *     druid的配置信息
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 11:55
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidConfig {

    private String loginUsername;

    private String loginPassword;

    private String allow;

    private String deny;

    private String resetEnable;
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        Console.log("servletRegistrationBean configure start.");
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow",allow);
        //黑名单,如果与白名单冲突,优先白名单配置
        servletRegistrationBean.addInitParameter("deny", deny);
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        //是否可以重置
        servletRegistrationBean.addInitParameter("resetEnable",resetEnable);
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        Console.log("filterRegistrationBean configure start.");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public void setDeny(String deny) {
        this.deny = deny;
    }

    public void setResetEnable(String resetEnable) {
        this.resetEnable = resetEnable;
    }
}
