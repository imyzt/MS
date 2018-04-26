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
    SUCCESS(200),

    /**
     * 未找到
     */
    NOT_FOUND(404),

    /**
     * 未认证
     */
    UNAUTHORIZED(401),

    /**
     * 失败
     */
    FALN(400),

    /**
     * 服务器内部错误
     */
    SERVER_ERROR(500);

    public int code;

    RetCode(int code){
        this.code = code;
    }
}
