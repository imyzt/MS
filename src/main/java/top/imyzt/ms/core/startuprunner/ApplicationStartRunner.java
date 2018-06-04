package top.imyzt.ms.core.startuprunner;

import cn.hutool.core.lang.Console;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 通过实现ApplicationRunner,重写run()方法,实现对系统加载时资源等内容的加载.
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
@Component
@Order(value = 2)
public class ApplicationStartRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //加载资源可放在此处

        Console.log("系统加载中,order = 2");
    }
}
