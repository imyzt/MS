package top.imyzt.ms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.catalina.User;
import top.imyzt.ms.model.UserInfo;

import java.util.List;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:31
 * @description: UserInfo的服务类接口
 */
public interface UserInfoService extends IService<UserInfo> {

    List<UserInfo> selList(Page<UserInfo> page);

}
