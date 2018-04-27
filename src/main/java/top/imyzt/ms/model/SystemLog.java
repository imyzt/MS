package top.imyzt.ms.model;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: imyzt
 * @date: 2018-04-27
 * @description: 系统日志表
 */
@TableName("system_log")
@ApiModel("系统日志表")
public class SystemLog extends Model<SystemLog> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 日志信息描述
     */
    @ApiModelProperty("日志信息描述")
    private String description;
    /**
     * 方法名称
     */
    @ApiModelProperty("方法名称")
    private String method;
    /**
     * 日志类型 0是正常，1是异常
     */
    @TableField("log_type")
    @ApiModelProperty("日志类型 0是正常，1是异常")
    private String logType;
    /**
     * 请求的ip
     */
    @TableField("request_ip")
    @ApiModelProperty("请求的ip")
    private String requestIp;
    /**
     * 异常错误码
     */
    @TableField("exception_code")
    @ApiModelProperty("异常错误码")
    private String exceptionCode;
    /**
     * 异常详情
     */
    @TableField("exception_detail")
    @ApiModelProperty("异常详情")
    private String exceptionDetail;
    /**
     * 请求参数
     */
    @ApiModelProperty("请求参数")
    private String params;
    /**
     * 请求的用户id
     */
    @TableField("user_id")
    @ApiModelProperty("请求的用户id")
    private String userId;
    @TableField("create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
        "id=" + id +
        ", description=" + description +
        ", method=" + method +
        ", logType=" + logType +
        ", requestIp=" + requestIp +
        ", exceptionCode=" + exceptionCode +
        ", exceptionDetail=" + exceptionDetail +
        ", params=" + params +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
