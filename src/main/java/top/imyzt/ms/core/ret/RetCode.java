package top.imyzt.ms.core.ret;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 12:55
 * @description: 响应码枚举
 */
public enum RetCode {


    /**
     * 成功
     */
    SUCCESS(200,"成功"),

    /**
     * 未找到
     */
    NOT_FOUND(404,"未找到"),

    /**
     * 未认证
     */
    UNAUTHORIZED(401,"未认证"),

    /**
     * 失败
     */
    FALN(400,"失败"),

    /**
     * 服务器内部错误
     */
    SERVER_ERROR(500,"服务器内部错误");

    private int code;
    private String msg;

    RetCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
