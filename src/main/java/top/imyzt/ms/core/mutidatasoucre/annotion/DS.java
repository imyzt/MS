package top.imyzt.ms.core.mutidatasoucre.annotion;

import java.lang.annotation.*;

/**
 *
 * <p>
 *     多数据源标识注解
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

    /**
     * 指定数据源的枚举类型
     * package top.imyzt.ms.common.enumerate.DSEnum
     * @return
     */
    String dsname() default "";
}
