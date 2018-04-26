package top.imyzt.ms.core.exception;

import top.imyzt.ms.core.ret.RetCode;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 14:01
 * @description: 自定义异常
 */
public class MsException extends RuntimeException{

    private int code;

    /**
     * 自定义异常01
     * @param retCode @see RetCode 类型的枚举信息
     */
    public MsException(RetCode retCode) {
        super(retCode.getMsg());
        this.code = retCode.getCode();
    }

    /**
     * 自定义异常02
     * @param msg 异常信息,错误码默认为500
     */
    public MsException(String msg) {
        super(msg);
        this.code = 500;
    }

    /**
     * 自定义异常03
     * @param code 错误码
     * @param msg 错误信息
     */
    public MsException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
