package top.imyzt.ms.core.ret;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 13:01
 * @description: 返回对象的实体类
 */
public class RetResult<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public RetResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 枚举消息状态
     * @param retCode 枚举类
     * @return
     */
    public RetResult<T> setCode(RetCode retCode) {
        this.code = retCode.getCode();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
