package top.imyzt.ms.core.configurer;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.imyzt.ms.core.filter.XssFilter;

import java.util.Arrays;

/**
 * <p>
 *     Filter过滤Xss,配置文件
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
@Configuration
public class XssFilterConfigurer {

    @Value("${ms.xssFilter.urlExclusion}")
    private String urlExclusion;

    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean(){
        XssFilter xssFilter = new XssFilter();
        //从yml文件中读取,排除不需要过滤的请求
        if (StrUtil.isNotBlank(urlExclusion)){
            String[] urls = urlExclusion.split(",");
            xssFilter.setUrlExclusion(Arrays.asList(urls));
        }
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(xssFilter);

        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");

        //default = 1000
        filterRegistrationBean.addInitParameter("sessionStatMaxCount", "3000");

        //可以配置principalCookieName，使得druid知道指定的sessionName是谁
        //filterRegistrationBean.addInitParameter("principalSessionName", "sessionId");

        //druid 0.2.7版本开始支持profile，配置profileEnable能够监控单个url调用的sql列表。
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

}
