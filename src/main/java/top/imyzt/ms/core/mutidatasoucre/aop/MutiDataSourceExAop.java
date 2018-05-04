package top.imyzt.ms.core.mutidatasoucre.aop;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import top.imyzt.ms.core.mutidatasoucre.annotion.DS;
import top.imyzt.ms.core.mutidatasoucre.config.DataSourceContextHolder;
import top.imyzt.ms.core.mutidatasoucre.datasource.MutiDataSourceProperties;

import java.lang.reflect.Method;

/**
 *
 * <p>
 *     多数据源切换AOP,拦截@DS注解自动切换
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/4 10:14
 */

@Aspect
@Component
@ConditionalOnProperty(prefix = "ms", name = "muti-datasource.muti-datasource-open", havingValue = "true")
public class MutiDataSourceExAop implements Ordered{

    Log log = LogFactory.get();

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    /**
     * 切入点
     * @return
     */
    @Pointcut(value = "@annotation(top.imyzt.ms.core.mutidatasoucre.annotion.DS)")
    private void cut(){

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;

        if (!(signature instanceof MethodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DS dataSource = currentMethod.getAnnotation(DS.class);
        if (dataSource != null){
            DataSourceContextHolder.setDataSourceType(dataSource.dsname());
            log.debug("设置数据源为：" + dataSource.dsname());
        }else {
            DataSourceContextHolder.setDataSourceType(mutiDataSourceProperties.getDefaultDataSourceName());
            log.debug("设置数据源为：默认数据源");
        }

        try {
            return point.proceed();
        } finally {
            log.debug("清空数据源信息! ");
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * AOP要优先事务先执行
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
