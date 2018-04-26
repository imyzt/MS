package top.imyzt.ms.core.page;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 16:27
 * @description: 分页参数.可在此创建多个分页方式.接收不同前台发送的分页参数
 */
public class PageFactory<T> {

    public Page<T> defaultPage(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> params = ServletUtil.getParamMap(request);

        //第几页
        Integer current = Integer.valueOf(params.get("current"));
        //每页大小
        Integer size = Integer.valueOf(params.get("size"));
        //排序字段
        String sort = params.get("sort");
        //asc或desc,升序或降序
        String order = params.get("order");

        if (StrUtil.isBlank(sort)){
            Page<T> page = new Page<>(current,size);
            page.setOpenSort(false);
            return page;
        }else {
            Page<T> page = new Page<>(current,size,sort);
            boolean asc = Order.ASC.getDes().equals(order);
            page.setAsc(asc);
            return page;
        }

    }
}
