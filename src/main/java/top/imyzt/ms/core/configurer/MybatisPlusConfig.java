package top.imyzt.ms.core.configurer;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:41
 * @description: mybatis-plus配置文件
 */
@EnableTransactionManagement
@Configuration                  //标识此类为配置文件类
@MapperScan("top.imyzt.ms.dao") //mybatis-plus扫描Mapper.java
public class MybatisPlusConfig {

    /**
     * 分页插件,自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
