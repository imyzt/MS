package top.imyzt.ms.core.mutidatasoucre.annotion;

import java.lang.annotation.*;

/**
 *
 * <p>
 *     多数据源标识
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/4 10:10
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {

    String dbname() default "";
}
