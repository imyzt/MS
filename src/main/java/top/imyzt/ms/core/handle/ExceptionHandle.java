package top.imyzt.ms.core.handle;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.imyzt.ms.core.exception.MsException;
import top.imyzt.ms.core.ret.RetCode;
import top.imyzt.ms.core.ret.RetResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 14:12
 * @description: 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 统一异常处理方法
     * 抛出异常的方法抛出异常{
     *      Execption.class
     *      MsException.class
     * }
     * 通过{
     *      new RetResult<T>().setCode(msException.getCode()).setMsg(msException.getMessage());
     *      new RetResult<T>().setCode(RetCode.SERVER_ERROR).setMsg(e.getMessage());
     * }
     * 将异常信息由统一的响应消息格式返回
     *
     * @param e 异常信息
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public<T> RetResult<T> handle(HttpServletRequest req, Object handler, Exception e){
        Log log = LogFactory.get();
        RetResult<T> result = new RetResult<>();
        String msg;

        /**
         * 自定义异常
         */
        if (e instanceof MsException){
            MsException msException = (MsException) e;
            result.setCode(msException.getCode()).setMsg(msException.getMessage());
            log.info(msException);
            return result;
        }

        /**
         * 404,访问的资源不存在
         */
        if (e instanceof NoHandlerFoundException){
            msg = "接口 [".concat(req.getRequestURI()).concat("] 不存在");
            result.setCode(RetCode.NOT_FOUND).setMsg(msg).setData(null);
            log.warn(msg);
            return result;
        }

        /**
         * 方法异常
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            msg = String.format("接口 [%s] 出现异常,方法:%s.%s,异常摘要:%s",req.getRequestURL(),
                    method.getBean().getClass().getName(), method.getMethod().getName(), e.getMessage());
            result.setCode(RetCode.FALN);
        }else { /** 其它所有异常 */
            msg = e.getMessage();
            result.setCode(RetCode.SERVER_ERROR);
        }

        log.error(e);
        return result.setMsg(msg).setData(null);

    }
}
