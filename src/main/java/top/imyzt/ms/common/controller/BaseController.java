package top.imyzt.ms.common.controller;

import com.baomidou.mybatisplus.plugins.Page;
import top.imyzt.ms.core.page.PageFactory;
import top.imyzt.ms.core.ret.RetCode;
import top.imyzt.ms.core.ret.RetResult;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 11:00
 * @description: 控制层的基类
 */
public abstract class BaseController {


    private final static String SUCCESS = "SUCCESS";
    private final static String UNAUTHORIZED = "UNAUTHORIZED";
    private final static String NOT_FOUND = "NOT_FOUND";


    /**
     * 成功的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(){
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    /**
     * 成功的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(String msg){
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(msg);
    }

    /**
     * 成功的消息返回
     * @param data 响应数据
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(T data){
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    /**
     * 成功的消息返回
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(String msg, T data){
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(msg).setData(data);
    }

    /**
     * 失败的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> faln(String msg){
        return new RetResult<T>().setCode(RetCode.FALN).setMsg(msg);
    }

    /**
     * 失败的消息返回
     * @param code 自定义响应码
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> faln(int code, String msg){
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    /**
     * 发生错误时的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> error(String msg){
        return new RetResult<T>().setCode(RetCode.SERVER_ERROR).setMsg(msg);
    }

    /**
     * 未找到内容的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> notFound(){
        return new RetResult<T>().setCode(RetCode.NOT_FOUND).setMsg(NOT_FOUND);
    }

    /**
     * 未认证的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> unauthorized(){
        return new RetResult<T>().setCode(RetCode.UNAUTHORIZED).setMsg(UNAUTHORIZED);
    }

    /**
     * 默认分页方式
     * @param <T>
     * @return
     */
    protected<T> Page<T> defaultPage(){
        return new PageFactory<T>().defaultPage();
    }


}
