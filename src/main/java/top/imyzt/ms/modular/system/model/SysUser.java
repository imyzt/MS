package top.imyzt.ms.modular.system.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 *
 * <p>
 *     用户信息
 * </p>
 *
 * @author: imyzt
 * @date: 2018-05-04
 */
@TableName("sys_user")
@ApiModel("用户信息")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("编号")
    private Integer id;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String uname;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", uname=" + uname +
        ", email=" + email +
        "}";
    }
}
