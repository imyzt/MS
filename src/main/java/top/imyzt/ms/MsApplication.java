package top.imyzt.ms;

import cn.hutool.core.lang.Console;
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
		Console.log("MS start success");
}
}
