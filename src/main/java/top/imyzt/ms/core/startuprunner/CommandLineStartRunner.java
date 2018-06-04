package top.imyzt.ms.core.startuprunner;

import cn.hutool.core.lang.Console;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 通过实现CommandLineRunner,重写run()方法,实现系统加载时资源的加载.
 * </p>
 * <p>
 *     与ApplicationRunner的区别是CommandLineRunner的run()参数是 <code>变长String</code>
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */

@Component
@Order(value = 3)
public class CommandLineStartRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        /**
         * 系统启动时需要加载的资源.可以修改Order的value修改启动顺序,越小优先级越高.
         */
        Console.log("CommandLineRunner启动,Order = 3");
    }
}
