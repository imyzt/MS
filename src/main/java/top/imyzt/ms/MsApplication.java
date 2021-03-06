package top.imyzt.ms;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author imyzt
 * @date 2018-4-26
 * @deprecated SpringBoot + Mybatis Plus搭建后台管理系统
 */
@SpringBootApplication
public class MsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsApplication.class, args);
		Log log = LogFactory.get();
		log.info("MS START SUCCESS");
}
}
