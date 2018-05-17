package top.imyzt.ms.core.configurer;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.imyzt.ms.core.filter.XssFilter;

import java.util.HashMap;

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

    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new XssFilter());
        filterBean.setOrder(Integer.MAX_VALUE - 1);
        filterBean.setEnabled(true);
        filterBean.addUrlPatterns("/*");

        HashMap<String, String> initParameters = new HashMap<>();

        //不需要参数过滤的请求url
        initParameters.put("excludes", "/favicon.ico,/image/*,/js/*,/css/*");
        //设置富文本是否需要过滤
        initParameters.put("isIncludeRichText", "true");

        filterBean.setInitParameters(initParameters);
        return filterBean;
    }

}
