package top.imyzt.ms.core.handle;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.imyzt.ms.core.exception.MsException;
import top.imyzt.ms.core.ret.RetCode;
import top.imyzt.ms.core.ret.RetResult;

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
    public<T> RetResult<T> handle(Exception e){
        Log log = LogFactory.get();
        if (e instanceof MsException){
            MsException msException = (MsException) e;
            log.warn(msException);
            return new RetResult<T>().setCode(msException.getCode()).setMsg(msException.getMessage());
        }else {
            log.error(e);
            return new RetResult<T>().setCode(RetCode.SERVER_ERROR).setMsg(e.getMessage());
        }
    }
}
